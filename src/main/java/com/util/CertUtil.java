package com.util;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.ssl.SSLContexts;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.FileInputStream;
import java.security.KeyStore;

public class CertUtil {
    /**
     * 加载证书
     */
    public static SSLConnectionSocketFactory initCert() throws Exception {
        FileInputStream instream = null;
        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        instream = new FileInputStream(new File(System.getProperty("user.dir")+"\\src\\main\\resources\\apiclient_cert.p12"));
        keyStore.load(instream, PropertyUtil.getProperty("wxpay.mchid").toCharArray());

        if (null != instream) {
            instream.close();
        }

        SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore,PropertyUtil.getProperty("wxpay.mchid").toCharArray()).build();
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[]{"TLSv1"}, null, SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);

        return sslsf;
    }
}
