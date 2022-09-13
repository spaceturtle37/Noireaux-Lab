// This macro demonstrates how to display a dialog box.
// The dialog it creates contains one string field, one
// popup menu, two numeric fields and one check box.


default_bool = false;
default_string = "";

methods = newArray("Automated","Manual");
channels = newArray("BF", "FITC", "TexRed");
days = newArray("D0","D1","D2");
  
// first ask if the naming convention is manual or automated
  
Dialog.create("Manual or automated?");
Dialog.addChoice("Acquisition method:", methods,methods[1]);
Dialog.show();
// no need to close dialog box it does itself
type = Dialog.getChoice();
  

// loop over multiple filters
  
retry = true;
counter = 0;

while (retry){
	  
	/////////////////////////////////////////////////////////////////////////////////
	// automated case

	if (type == methods[0]){
				
		// first time initialize variables to default values
		// future times we will take previous values
		if (counter==0){
			
			channel = channels[0]; 
			location_check = default_bool; 
			time_check = default_bool;
			slice_check = default_bool;
			
			s = default_string;
			t = default_string;
			z = default_string;
			
		};
		
		//  create a new box
		Dialog.create("Generate Filter");
		Dialog.addChoice("Channel:", channels,channel);
		Dialog.addCheckbox("Locations?", location_check);
		Dialog.addString("Location:",s);
		Dialog.addCheckbox("Times?", time_check);
		Dialog.addString("Time:",t);
		Dialog.addCheckbox("Z Slices?", slice_check);
		Dialog.addNumber("Z Slice:",z);
		//Dialog.addString("Z Slice (2 digits):","");
		
		// retrieve box values
		Dialog.show();
		channel = Dialog.getChoice();
		location_check = Dialog.getCheckbox();
		s = Dialog.getString();
		time_check = Dialog.getCheckbox();
		t = Dialog.getString();
		slice_check = Dialog.getCheckbox();
		//z = Dialog.getString();
		z = Dialog.getNumber();

		filter = "(.*";
		filter = filter + channel;
		filter = filter + ".*";
		
		filter = filter + "_s";
		if (location_check){
			filter =  filter + s;
		}else{
			filter = filter + ".*";
		};
		filter = filter + "_t";
		if (time_check){
			filter =  filter + t;
		}else{
			filter = filter + ".*";
		};
		filter = filter + "_z";
		if (slice_check){
			if (z<10){
				z = "0" + z ;
			};
			filter =  filter + z;
		};
		
		filter = filter + ".*)";
		
		
	///////////////////////////////////////////////////////////////////////////////
	// manual naming case

	} else if (type == methods[1]){
		
		// first time initialize variables to default values
		// future times we will take previous values
		if (counter==0){
			
			channel = channels[0]; 
			day = days[0];
			check1 = default_bool; 
			check2 = default_bool;
			check3 = default_bool;
			
			tag1 = default_string;
			tag2 = default_string;
			tag3 = default_string;
			
		};
		
		//  create a new box
		Dialog.create("Generate Filter");
		Dialog.addChoice("Channel:", channels,channel);
		Dialog.addChoice("Day:", days,day);
		Dialog.addCheckbox("Tag1?", check1);
		Dialog.addString("Tag1:",tag1);
		Dialog.addCheckbox("Tag2?", check2);
		Dialog.addString("Tag2:",tag2);
		Dialog.addCheckbox("Tag3?", check3);
		Dialog.addString("Tag3:",tag3);
		
		// retrieve box values
		Dialog.show();
		channel = Dialog.getChoice();
		day = Dialog.getChoice();
		check1 = Dialog.getCheckbox();
		tag1 = Dialog.getString();
		check2 = Dialog.getCheckbox();
		tag2 = Dialog.getString();
		check3 = Dialog.getCheckbox();
		tag3 = Dialog.getString();

		filter = "(.*";

		if (check1){
			filter = filter + tag1 + "_";
			if (check2){
				filter = filter + ".*" + tag2 + "_";
				if (check3){
					filter = filter + ".*" + tag3 + "_";
				};
			};
			// if we use any extra tags add an extra space before adding day and channel tag
			filter = filter + ".*";
		};

		filter = filter + day + ".*" + channel;
		filter = filter + ".*)";
	};
	  
	print(filter);
	
	
	/////////////////////////////////////////////////////////////////////////////////////
	// run script with the desired filter 

	path_scripts = "J:/Lab members/graduate students/Velasco/ImageJ Scripts/";
	ending = ".java";

	actions = newArray(channel+" processing","video","mean stdev");
	//folders = newArray("Image Processing/","Kinetic Video/","Histograms/");
	folder = "Arg/";
	nsize = actions.length;
	
	//initialize empty array only first time
	if (counter==0){
		checks = newArray(nsize);
	};
	
	//  create a new box
	Dialog.create("Action");
	Dialog.addMessage(filter);
	for (i = 0; i < nsize; i++){
		
		// first time initialize variables to default values
		// future times we will take previous values
		if (counter==0){
			checks[i] = default_bool; 
		};
		Dialog.addCheckbox(actions[i], checks[i]);
	};
	
	// read the values from box
	Dialog.show();
	for (i = 0; i < nsize; i++){
		checks[i] = Dialog.getCheckbox();
		// do stuff for only those which are ticked on
		if(checks[i]){
			//runMacro( path_scripts + folders[i] + actions[i] + " arg" + ending , filter );
			runMacro( path_scripts + folder + actions[i] + " arg" + ending , filter );
		};
	};


	///////////////////////////////////////////////////////////////////////////////////////////
	//  check if you want to keep going
	
	Dialog.create("Filter");
	Dialog.addMessage("Run#: " + (counter+1));
	Dialog.addCheckbox("Go again?", true);
	Dialog.show();
	retry = Dialog.getCheckbox();
	counter+=1;
	
};
 


