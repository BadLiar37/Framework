package service;

import model.User;

public class UserCreator {
    public static final String TESTDATA_USER_PHONENUMBER = "test.data.phoneNumber";
    public static final String TESTDATA_USER_PASSWORD = "test.data.password";

    public static User withCredentialsFromProperty(){
        return new User(TestDataReader.getTestData(TESTDATA_USER_PHONENUMBER),
                TestDataReader.getTestData(TESTDATA_USER_PASSWORD));
    }

    public static User withEmptyUsername(){
        return new User("", TestDataReader.getTestData(TESTDATA_USER_PASSWORD));
    }

    public static User withEmptyPassword(){
        return new User(TestDataReader.getTestData(TESTDATA_USER_PHONENUMBER), "");
    }
}