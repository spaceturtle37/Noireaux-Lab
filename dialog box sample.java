// This macro demonstrates how to display a dialog box.
// The dialog it creates contains one string field, one
// popup menu, two numeric fields and one check box.

// initialize values to use as guess
  
title = "Untitled";
width=512; height=512;


// chose dialog box prompts and text

types = newArray("8-bit", "16-bit", "32-bit", "RGB");
Dialog.create("Example Dialog");
Dialog.addString("Title:", title);
Dialog.addChoice("Type:", types);
Dialog.addNumber("Width:", width);
Dialog.addNumber("Height:", height);
Dialog.addCheckbox("Ramp", true);

    
// update the values stored 

Dialog.show();
  
  
// store the new values on variables

title = Dialog.getString();
width = Dialog.getNumber();
height = Dialog.getNumber();;
type = Dialog.getChoice();
ramp = Dialog.getCheckbox();
  
if (ramp)
	type += " ramp";
else
	type += " black";
 

// use variables to make a picture 
 
newImage(title, type, width, height, 1);
 