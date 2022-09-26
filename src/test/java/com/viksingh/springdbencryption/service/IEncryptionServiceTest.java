package com.viksingh.springdbencryption.service;

import com.viksingh.springdbencryption.service.impl.IEncryptionServiceImpl;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class IEncryptionServiceTest {


    private static final String textToBeTest = "VIKASH SINGH";
    private static String encryptedText;
    @InjectMocks
    private IEncryptionServiceImpl<String> iEncryptionService;

    @Test
    void encryptionTest() {
        encryptedText = iEncryptionService.encrypt(textToBeTest);
        Assert.assertNotNull("can not be null", encryptedText);
        System.out.println(encryptedText);
    }

    @Test
    void decryptTest() {
        String decryptText = new String(iEncryptionService.decrypt(encryptedText));
        System.out.println("Decrypted text ->" + decryptText.replaceAll("[^a-zA-Z0-9 ]", ""));
        Assert.assertNotNull("can not be null", new String(decryptText).replaceAll("[^a-zA-Z0-9 ]", ""));
        Assert.assertEquals(decryptText.replaceAll("[^a-zA-Z0-9 ]", ""), textToBeTest);
    }

}