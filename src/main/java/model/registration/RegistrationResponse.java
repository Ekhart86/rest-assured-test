package model.registration;


public  class RegistrationResponse {

    private boolean isSuccessful;
    private String result = null;
    private int code;


    public boolean getIsSuccessful() {
        return isSuccessful;
    }

    public String getResult() {
        return result;
    }

    public int getCode() {
        return code;
    }

    public void setIsSuccessful(boolean isSuccessful) {
        this.isSuccessful = isSuccessful;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setCode(int code) {
        this.code = code;
    }


}