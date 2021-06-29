package ooc.webapp.service;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class Hashing {

    public boolean verifyPassword(String password, String bcryptHashString){
        BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), bcryptHashString);
        return result.verified;
    }

    public String getHash(String password){
        return BCrypt.withDefaults().hashToString(12, password.toCharArray());
    }


}
