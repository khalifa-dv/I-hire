/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Validation;

import static java.awt.SystemColor.text;
import java.util.Date;

/**
 *
 * @author e.bentijani
 */
public class TestQuestionValidation {
    public static String ifInputStringEmptyDO(String nameInput, String input)
    {
        if (input.trim().isEmpty())
            return "* Champ "+nameInput+" obligatoire.";
        else
            return "";
    }
     public static String validateStringOfNumber(String nameInput, String ch)
     {
         if (ch.trim().isEmpty())
          return ifInputStringEmptyDO("Nombre de question à selectionné", ch);
          if(! ch.matches("[0-9]+") || Integer.valueOf(ch)<= 0)
          {
            return nameInput+" invalide.";
          }
          else return "";
     }
}