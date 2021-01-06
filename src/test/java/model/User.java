package model;

import lombok.*;

import com.google.common.base.Preconditions;


@Getter
@Setter
@ToString
@EqualsAndHashCode
public class User {
        private  String phoneNumber;
        private  String password;

        public User(String phoneNumber,String password){
            Preconditions.checkNotNull(phoneNumber);
            Preconditions.checkNotNull(password);
            this.phoneNumber = phoneNumber;
            this.password = password;
        }
}


