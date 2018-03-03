package ru.ajana.claim.web.mvc.rest;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.ajana.claim.exception.ClaimNotFoundException;
import ru.ajana.claim.model.Claim;
import ru.ajana.claim.model.Response;
import ru.ajana.claim.web.service.ClaimService;

/**
 * Модульный тест для ClaimRestController.
 *
 * @author Andrey Kharintsev
 * @date 22.07.2017.
 */
public class ClaimRestControllerTest {

  private Claim claim;
  private final List<Claim> claimList = new ArrayList<>();
  private final ClaimService claimService = mock(ClaimService.class);
  private final ClaimRestController claimRestController = new ClaimRestController(claimService);

  @Before
  public void initClaimList() {
    claim = new Claim();
    claim.setId("123");
    claim.setName("Заявка 1");
    claim.setFromAccountId(123L);
    claim.setToAccountId(321L);
    claim.setStatusId(1L);
    claimList.add(claim);
  }

  @Test
  public void testGetClaims() {
    when(claimService.findAllClaims()).thenReturn(claimList);
    //если нет конструктора, можно так
    //ReflectionTestUtils.setField(claimRestController, "claimService", claimService);
    assertEquals(1, claimRestController.getClaims().size());
  }

  @Test(expected = ClaimNotFoundException.class)
  public void shouldClaimNotFoundExceptionGetClaim() {
    when(claimService.getClaim(321L)).thenReturn(claim);
    claimRestController.getClaim(Long.valueOf(claim.getId()));
  }

  @Test
  public void testGetClaim() {
    when(claimService.getClaim(123L)).thenReturn(claim);
    final Claim actualClaim = claimRestController.getClaim(Long.valueOf(claim.getId()));
    assertEquals(claim.getId(), actualClaim.getId());
  }

  @Test
  public void testCreateClaim() {
    final Claim newClaim = new Claim();
    newClaim.setId(null);
    newClaim.setName("Заявка 1");
    newClaim.setFromAccountId(123L);
    newClaim.setToAccountId(321L);
    newClaim.setStatusId(1L);
    when(claimService.save(newClaim)).thenAnswer(invocation -> {
      claimList.add(claim);
      return claim;
    });

    ResponseEntity<Response> response = claimRestController.createClaim(newClaim);
    final Claim actualClaim = (Claim) response.getBody().getData();
    assertEquals(claim.getId(), actualClaim.getId());
    assertEquals(claim.getName(), actualClaim.getName());
    assertEquals(2, claimList.size());
  }

  @Test
  public void testUpdateClaim() {
    claim.setName("Заявка - обновилось наименование");

    final Claim updClaim = new Claim();
    updClaim.setId("123");
    updClaim.setName("Заявка - обновилось наименование");
    updClaim.setFromAccountId(123L);
    updClaim.setToAccountId(321L);
    updClaim.setStatusId(1L);

    when(claimService.getClaim(123L)).thenReturn(claim);
    when(claimService.save(claim)).thenReturn(updClaim);

    final ResponseEntity<Response> response = claimRestController.updateClaim(claim);
    final Claim actualClaim = (Claim) response.getBody().getData();
    assertEquals(updClaim.getName(), actualClaim.getName());
  }

  @Test
  public void testDeleteClaim() {
    final ResponseEntity<Void> response = claimRestController.deleteClaim(claim);
    assertEquals(HttpStatus.OK, response.getStatusCode());
  }

  @Test
  public void shouldBadRequestDeleteClaim() {
    final ResponseEntity<Void> response = claimRestController.deleteClaim(null);
    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

    claim.setId(null);
    final ResponseEntity<Void> response1 = claimRestController.deleteClaim(claim);
    assertEquals(HttpStatus.BAD_REQUEST, response1.getStatusCode());
  }
}