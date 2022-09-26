package com.viksingh.springdbencryption.service;

import java.io.Serializable;

public interface IEncryptionService<T extends Serializable> {

    String encrypt(T data);

    byte[] decrypt(T data);

}
