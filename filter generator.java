// This macro demonstrates how to display a dialog box.
// The dialog it creates contains one string field, one
// popup menu, two numeric fields and one check box.

default_bool = false;
method = newArray("Automated","Manual");
channels = newArray("BF", "FITC", "TexRed");
days = newArray("D0","D1","D2");
  
// first ask if the naming convention is manual or automated
  
Dialog.create("Manual or automated?");
Dialog.addChoice("Acquisition method:", method);
Dialog.show();
// no need to close dialog box it does itself
type = Dialog.getChoice();
  
  
//  create a new box
Dialog.create("Generate Filter");
Dialog.addChoice("Channel:", channels);
  
filter = "(.*";
  
  
// automated case

if (type == method[0]){
	
	Dialog.addCheckbox("Locations?", default_bool);
	Dialog.addString("Location:","");
	Dialog.addCheckbox("Times?", default_bool);
	Dialog.addString("Time:","");
	Dialog.addCheckbox("Z Slices?", default_bool);
	Dialog.addNumber("Z Slice:","");
	//Dialog.addString("Z Slice (2 digits):","");
	
	Dialog.show();
	channel = Dialog.getChoice();
	location_check = Dialog.getCheckbox();
	s = Dialog.getString();
	time_check = Dialog.getCheckbox();
	t = Dialog.getString();
	slice_check = Dialog.getCheckbox();
	//z = Dialog.getString();
	z = Dialog.getNumber();

	filter = filter + channel;
	if (location_check){
		// or statement |
		// if anything follows after location
		//if (time_check | slice_check){
		//	filter = filter + ".*" + "s" + s + "_";
		//} else { 
		//filter =  filter + ".*" + "s" + s + ".TIFF";
		//};
		filter =  filter + ".*" + "_s" + s;
	};
	if (time_check){
		//if (slice_check){
		//	filter = filter + ".*" + "t" + t + "_";
		//} else {
		//filter = filter + ".*" + "t" + t + ".TIFF";
		//};
		filter =  filter + ".*" + "_t" + t;
	};
	if (slice_check){
		if (z<10){
			z = "0" + z ;
		};			
		//filter = filter + ".*" + "z" + z + ".TIFF" ; 
		filter =  filter + ".*" + "_z" + z;
	};
	
// manual naming case

} else if (type == method[1]){
	
	Dialog.addChoice("Day:", days);
	Dialog.addCheckbox("Tag1?", default_bool);
	Dialog.addString("Tag1:","");
	Dialog.addCheckbox("Tag2?", default_bool);
	Dialog.addString("Tag2:","");
	Dialog.addCheckbox("Tag3?", default_bool);
	Dialog.addString("Tag3:","");
	
	Dialog.show();
	channel = Dialog.getChoice();
	day = Dialog.getChoice();
	check1 = Dialog.getCheckbox();
	tag1 = Dialog.getString();
	check2 = Dialog.getCheckbox();
	tag2 = Dialog.getString();
	check3 = Dialog.getCheckbox();
	tag3 = Dialog.getString();

	if (check1){
		filter = filter + tag1;
		if (check2){
			filter = filter + ".*" + tag2;
			if (check3){
				filter = filter + ".*" + tag3;
			};
		};
		// if we use any extra tags add an extra space before adding day and channel tag
		filter = filter + ".*";
	};

	filter = filter + day + ".*" + channel;
	
};
  
filter = filter + ".*)";
print(filter);




  
  
  
  
  
 


