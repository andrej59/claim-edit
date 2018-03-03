package ru.ajana.claim.web.mvc.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.ajana.claim.exception.ClaimNotFoundException;
import ru.ajana.claim.exception.InvalidInputException;
import ru.ajana.claim.exception.InvalidValueException;
import ru.ajana.claim.exception.RequireValueEcxeption;
import ru.ajana.claim.model.Claim;
import ru.ajana.claim.model.ClaimStatus;
import ru.ajana.claim.model.Response;
import ru.ajana.claim.web.service.ClaimService;

/**
 * Rest - контроллер заявок.
 *
 * @author Andrey Kharintsev
 * @date 27.06.2017.
 */
@RestController
@RequestMapping("/rest/claims")
public class ClaimRestController extends AbstractRestController {

  private final static Logger LOG = LoggerFactory.getLogger(ClaimRestController.class);
  private final static ValidatorFactory VALIDATOR_FACTORY;
  private final ClaimService claimService;

  static {
    VALIDATOR_FACTORY = Validation.buildDefaultValidatorFactory();
  }

  @Autowired
  public ClaimRestController(final ClaimService claimService) {
    this.claimService = claimService;
  }

  @RequestMapping(method = RequestMethod.GET, path = "/view")
  public List<Claim> getClaims() {
    LOG.info("execute");
    return claimService.findAllClaims();
  }

  @RequestMapping(method = RequestMethod.GET, value = "/{id}")
  public Claim getClaim(@PathVariable final Long id) {
    LOG.info("execute");
    final Claim claim = this.claimService.getClaim(id);
    if (claim == null) {
      throw new ClaimNotFoundException(String.valueOf(id));
    }
    return claim;
  }

  @RequestMapping(method = RequestMethod.POST, path = "/create")
  public ResponseEntity<Response> createClaim(@RequestBody final Claim claim) {
    LOG.info("execute");
    try {
      validateClaim(claim);
      final Claim result = claimService.save(claim);
      return ok(result, "Заявка создана успешно");
    } catch (RequireValueEcxeption | InvalidValueException e) {
      return badRequest(claim, e.getMessage());
    } catch (InvalidInputException e) {
      return badRequest(claim, e.getErrors());
    }
  }

  @RequestMapping(method = RequestMethod.POST, path = "/update")
  public ResponseEntity<Response> updateClaim(@RequestBody final Claim claim) {
    LOG.info("execute");
    try {
      validateClaim(claim);
      final Claim result = claimService.save(claim);
      return ok(result, "Заявка обновлена успешно");
    } catch (ClaimNotFoundException e) {
      return notFound();
    } catch (InvalidInputException e) {
      return badRequest(null, e.getErrors());
    }
  }

  @RequestMapping(method = RequestMethod.POST, path = "/delete")
  public ResponseEntity<Void> deleteClaim(@RequestBody  final Claim claim) {
    LOG.info("execute");
    if (claim == null || claim.getId() == null) {
      return badRequest();
    }

    claimService.delete(Long.getLong(claim.getId()));

    return ok(null, "Заявка успешно удалена");
  }

  @RequestMapping(method = RequestMethod.GET, path = "/statuses")
  public List<ClaimStatus> getStatusList() {
    LOG.info("execute");
    return claimService.getClaimStatusList();
  }

  /**
   * Метод выполняет проверку данных при создании заявки.
   *
   * @param claim заявка
   */
  private void validateClaim(final Claim claim) {
    if (claim != null) {
      final Validator validator = VALIDATOR_FACTORY.getValidator();
      final Set<ConstraintViolation<Claim>> cvs = validator
          .validate(claim);

      if (cvs.size() > 0) {
        final List<String> errors = new ArrayList<>();
        for (ConstraintViolation<Claim> cv : cvs) {
          final String fieldName = cv.getPropertyPath().toString();
          final String message = cv.getMessage();
          errors.add(
              String
                  .format("Значение поля '%s' установлено не верно, ошибка: %s", fieldName,
                      message));

        }
        throw new InvalidInputException(errors);
      }
    }
    if (claim.getId() != null) {
      long claimId = NumberUtils.toLong(claim.getId(), 0);
      if (claimId > 0) {
        Optional.of(this.claimService.getClaim(claimId)).orElseThrow(
            () -> new ClaimNotFoundException(String.valueOf(claim.getId())));
      }
    }
  }
}
