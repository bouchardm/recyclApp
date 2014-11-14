/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Presentation.Swing;

import java.util.regex.Pattern;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

/**
 *
 * @author Dany
 */
public class RegexTextField extends JTextField
{
    private String _regex = "\\d*";
    
    public void setRegexPattern(String pattern)
    {
        Pattern.compile(pattern);
        _regex = pattern;
    }
    
    @Override
    protected Document createDefaultModel()
    {
        Document doc = new NumericDocument(_regex);
        return doc;
    }

    private static class NumericDocument extends PlainDocument
    {
        private final String _regex;
        private final Pattern DIGITS;
        
        public NumericDocument(String regex)
        {
            _regex = regex;
            DIGITS = Pattern.compile(_regex);
        }
        
        
        // The regular expression to match input against (zero or more digits)
        

        @Override
        public void insertString(int offs, String str, AttributeSet a) throws BadLocationException
        {
            // Only insert the text if it matches the regular expression
            if (str != null && DIGITS.matcher(str).matches())
                super.insertString(offs, str, a);
        }
    }
}
