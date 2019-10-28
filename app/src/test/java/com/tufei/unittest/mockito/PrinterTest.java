package com.tufei.unittest.mockito;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author TuFei
 * @date 2019/10/26.
 */
public class PrinterTest {
    @Spy
    private Printer printer;
    @Mock
    Callback callback;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testPrinterPDFSuccess() {
        doReturn("D:\\demo\\a.jpg").when(printer).transform(anyString());
        printer.print("D:\\demo\\a.pdf", callback);
        verify(callback).onSucess("jpg");
    }
}
