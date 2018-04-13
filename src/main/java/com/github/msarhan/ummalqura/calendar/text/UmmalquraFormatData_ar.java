/*
 * The MIT License
 *
 * Copyright 2015 Mouaffak A. Sarhan
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.github.msarhan.ummalqura.calendar.text;

import java.util.ListResourceBundle;

/**
 * @author Mouaffak A. Sarhan.
 */
public class UmmalquraFormatData_ar extends ListResourceBundle {

    @Override
    protected Object[][] getContents() {
        return new Object[][]{
                {"MonthNames",
                        new String[]{
                                "\u0645\u062D\u0631\u0645",
                                "\u0635\u0641\u0631",
                                "\u0631\u0628\u064A\u0639 \u0627\u0644\u0623\u0648\u0644",
                                "\u0631\u0628\u064A\u0639 \u0627\u0644\u062B\u0627\u0646\u064A",
                                "\u062C\u0645\u0627\u062F\u0649 \u0627\u0644\u0623\u0648\u0644\u0649",
                                "\u062C\u0645\u0627\u062F\u0649 \u0627\u0644\u0622\u062E\u0631\u0629",
                                "\u0631\u062C\u0628",
                                "\u0634\u0639\u0628\u0627\u0646",
                                "\u0631\u0645\u0636\u0627\u0646",
                                "\u0634\u0648\u0627\u0644",
                                "\u0630\u0648 \u0627\u0644\u0642\u0639\u062F\u0629",
                                "\u0630\u0648 \u0627\u0644\u062D\u062C\u0629"
                        }
                },
                {"MonthAbbreviations",
                        new String[]{
                                "\u0645\u062D\u0631\u0645",
                                "\u0635\u0641\u0631",
                                "\u0631\u0628\u064A\u0639 1",
                                "\u0631\u0628\u064A\u0639 2",
                                "\u062C\u0645\u0627\u062F\u0649 1",
                                "\u062C\u0645\u0627\u062F\u0649 2",
                                "\u0631\u062C\u0628",
                                "\u0634\u0639\u0628\u0627\u0646",
                                "\u0631\u0645\u0636\u0627\u0646",
                                "\u0634\u0648\u0627\u0644",
                                "\u0630\u0648 \u0627\u0644\u0642\u0639\u062F\u0629",
                                "\u0630\u0648 \u0627\u0644\u062D\u062C\u0629"
                        }
                }
        };
    }

}
