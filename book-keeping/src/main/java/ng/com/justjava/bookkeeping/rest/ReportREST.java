package ng.com.justjava.bookkeeping.rest;

import ng.com.justjava.bookkeeping.db.view.TrialBalanceView;
import ng.com.justjava.bookkeeping.services.ReportInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class ReportREST {
    @Autowired
    private ReportInterface reportInterface;

    @GetMapping(value = "/trialbalance")
    public ResponseEntity<?> postTransactionDetails(@DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss") LocalDateTime startDate,
                                                    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss") LocalDateTime endDate) {
        //System.out.println(" ENtering here........headers======...."+headers);

/*        try {
            String token = headers.get("authorization");
            token = token.replace("Bearer","");
            JWT jwt = JWTParser.parse(token.trim());
            System.out.println(" jwt.getJWTClaimsSet().toJSONObject()====="+
                    jwt.getJWTClaimsSet().getClaims().get("preferred_username"));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }*/

        List<TrialBalanceView> trialBalanceView = reportInterface.getTrialBalance(startDate,endDate);
        return ResponseEntity.ok(trialBalanceView);
    }
}
