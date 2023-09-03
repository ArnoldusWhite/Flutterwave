class TransfersService{

    // [--------- USING API ENDPOINTS ---------]

    def baseUrl = "https://api.flutterwave.com/v3/";

    // 1 - Api call for bank transfer ---------------------------------
    String url = baseUrl + "transfers";
    def getConnection =new URL(url).openConnection();
    getConnection.setRequestMethod("POST");
    getConnection.setDoOutput(true);
    getConnection.setRequestProperty("Content-Type", "application/json")
    getConnection.setRequestProperty("Authorization", authHeader);

    def data = JsonOutput.toJson([
        account_bank: "044",
        account_number : "0690000040",
        amount : 5500,
        narration : "Akhlm Pstmn Trnsfr xx007",
        currency : "NGN",
        reference : "akhlm-pstmnpyt-rfxx007_PMCKDU_1",
        callback_url: "https://www.flutterwave.com/ng/",
        debit_currency : "NGN"
    ]);
    getConnection.getOutputStream().write(data.getBytes("UTF-8"));
    int responseCode = getConnection.getResponseCode()
    boolean state = (responseCode >= 200 && responseCode < 300);
    if(state){
        inputStream = new InputStreamReader(getConnection.getInputStream())
        in=null;
        in= new BufferedReader(inputStream);
        outputString=""
        responseString=""
        while((responseString=in.readLine()) != null){
            outputString = outputString + responseString
        }
        
        def jsonResponse = new groovy.json.JsonSlurper()
        result = jsonResponse.parseText(outputString)
    }

    //Api call for bank transfer END ---------------------------------------

    // 2 - Api call for mobile money wallet transfer -----------------------
    String url = baseUrl + "transfers";
    def getConnection =new URL(url).openConnection();
    getConnection.setRequestMethod("POST");
    getConnection.setDoOutput(true);
    getConnection.setRequestProperty("Content-Type", "application/json")
    getConnection.setRequestProperty("Authorization", authHeader);

    def data = JsonOutput.toJson([
        account_bank: "MPS",
        account_number : "2540700000000",
        amount : 500,
        narration : "New transfer",
        currency : "KES",
        reference : "mk-902837-jk",
        beneficiary_name: "Flutterwave Developers",
    ]);

    getConnection.getOutputStream().write(data.getBytes("UTF-8"));
    int responseCode = getConnection.getResponseCode()
    boolean state = (responseCode >= 200 && responseCode < 300);
    if(state){
        inputStream = new InputStreamReader(getConnection.getInputStream())
        in=null;
        in= new BufferedReader(inputStream);
        outputString=""
        responseString=""
        while((responseString=in.readLine()) != null){
            outputString = outputString + responseString
        }
        
        def jsonResponse = new groovy.json.JsonSlurper()
        result = jsonResponse.parseText(outputString)
    }

    //Api call for bank transfer END -----------------------

    /*
     * For case 1, the transfer status will be update when the callback function will be call.
     * If not, after a certain time, if the callback_URL is not call, the status should be update.
     */
    
     /*
      * Case 2 Check the transfer status
      */
    String url = baseUrl + "transfers/"+ transferId;
    def getConnection = new URL(url).openConnection();
    getConnection.setRequestMethod("GET");
    getConnection.setDoOutput(true);
    getConnection.setRequestProperty("Content-Type", "application/json");
    getConnection.setRequestProperty("Authorization", "Bearer FLWSECK_TEST-SANDBOXDEMOKEY-X");
    responseCode = getConnection.getResponseCode();
    boolean state = (responseCode >= 200 && responseCode < 300);
    if(state){
        inputStream = new InputStreamReader(getConnection.getInputStream())
        in=null;
        in= new BufferedReader(inputStream);
        outputString=""
        responseString=""
        while((responseString=in.readLine()) != null){
            outputString = outputString + responseString
        }
        
        def jsonResponse = new groovy.json.JsonSlurper()
        result = jsonResponse.parseText(outputString)
    }
}
