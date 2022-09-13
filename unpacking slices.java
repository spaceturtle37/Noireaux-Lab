//************************************************************************
// ************************* Unpacking of the stacks *********************
//************************************************************************

// this will make all the pop-ups not show up
setBatchMode(true);

// base name of the pictures
base_name = "EutM2nM_";

root_folder = "C:/Users/Jcv5ff/Desktop/";
read_directory = root_folder + "Pool/";
path_unpack = root_folder + "Well/Kinetics/";
// look for output folder, if it doesnt exist make it
if (File.exists(path_unpack)==0) {
	File.makeDirectory(path_unpack);
};


// initial conditions for first file name
w = 1 ;
channel = "BF";
s = 1;
t = 1;

channel_name = "_w" + w + channel + " Camera";
location_name = "_s" + s;
time_name = "_t" + t;
file_name = base_name + channel_name + location_name + time_name; 
path_input = read_directory + file_name + ".TIF";

// print to make sure first file is found
print(path_input);
print(File.exists(path_input));


//print(path_input);
//print(File.exists(path_input));
while(File.exists(path_input)) {
	while (File.exists(path_input)) {
		while (File.exists(path_input)) {
			open(path_input);
			output_name = file_name + "_z.TIF";
			run("Duplicate...", "title=[" + output_name + "] duplicate");
			selectWindow(file_name + ".TIF");
			close();
			selectWindow(output_name);
			run("Image Sequence... ", "save=" + path_unpack + " format=TIFF digits=2 start=1");
			close();
			
			// update time name
			t = t + 1;
			time_name = "_t" + t;
			
			// update name of file to check for
			file_name = base_name + channel_name + location_name + time_name;
			//  update the total path of file to check for
			path_input = read_directory + file_name + ".TIF";
			
		};
		
		// reset time counter for next slice
		t = 1;
		time_name = "_t" + t;
		// update slice name
		s = s + 1;
		location_name = "_s" + s;
		
		// update name of file to check for
		file_name = base_name + channel_name + location_name + time_name;
		//  update the total path of file to check for
		path_input = read_directory + file_name + ".TIF";
		
	};
	
	// reset slice counter for next wavelength
	s = 1;
	location_name = "_s" + s;
	// update channel to FITC
	w = w + 1;
	channel = "FITC";
	channel_name = "_w" + w + channel + " Camera";
	
	// update name of file to check for
	file_name = base_name + channel_name + location_name + time_name;
	// update the total path of file to check for
	path_input = read_directory + file_name + ".TIF";
		
};

//print("done");
// this will make all the pop-ups show up again
setBatchMode(false);
