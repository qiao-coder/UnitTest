package com.tufei.unittest.mockito;

import androidx.annotation.VisibleForTesting;

/**
 * @author TuFei
 * @date 2019/10/26.
 */
public class Printer {
    public void print(String filePath, Callback callback) {
        if (getFormat(filePath).equals("pdf")) {
            String newFilePath = transform(filePath);
            jumpToPrinterShare(newFilePath, callback);
        } else {
            jumpToPrinterShare(filePath, callback);
        }
    }

    private void jumpToPrinterShare(String file, Callback callback) {
        callback.onSucess(getFormat(file));
    }

    @VisibleForTesting
    protected String transform(String filePath) {
        return null;
    }

    private String getFormat(String filePath) {
        return filePath.substring(filePath.lastIndexOf('.') + 1);
    }


}
