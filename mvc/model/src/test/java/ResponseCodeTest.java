import com.jrest.mvc.model.ResponseCode;

public class ResponseCodeTest {

    public static void main(String[] args) {
        System.out.println("[102] isInformational(): " + ResponseCode.fromCode(102).isInformational());
        System.out.println("[200] isSuccessful(): " + ResponseCode.fromCode(200).isSuccessful());
        System.out.println("[301] isRedirection(): " + ResponseCode.fromCode(301).isRedirection());
        System.out.println("[422] isClientError(): " + ResponseCode.fromCode(422).isClientError());
        System.out.println("[599] isServerError(): " + ResponseCode.fromCode(599).isServerError());
        System.out.println("[600] isUnknownType(): " + ResponseCode.fromCode(600).isUnknownType());
        System.out.println("[97] isUnknownType(): " + ResponseCode.fromCode(97).isUnknownType());
        System.out.println();
        System.out.println("422 " + ResponseCode.getCodeMessage(ResponseCode.UNPROCESSABLE_ENTITY));
        System.out.println("200 " + ResponseCode.getCodeMessage(ResponseCode.OK));
        System.out.println("301 " + ResponseCode.getCodeMessage(ResponseCode.MOVED_PERMANENTLY));
    }
}
