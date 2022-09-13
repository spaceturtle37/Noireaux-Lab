//************************************************************************
// ************************** Making videos ******************************
//************************************************************************

// this will make all the pop-ups not show up
setBatchMode(true);

// base name of the pictures
base_name = "EutM2nM_";
// set frame rate for videos
framerate =  5;

root_folder = "C:/Users/Jcv5ff/Desktop/";
path_sorted = root_folder + "Well/Sorted/";
path_videos = root_folder + "Well/Videos/";
// look for output folder, if it doesnt exist make it
if (File.exists(path_videos)==0) {
	File.makeDirectory(path_videos);
};


// indices initialization
w = 1;
channel = "BF";
s = 1;
t = 1;
z = 1;

// target file name definition
channel_name = "_w" + w + channel + " Camera";
location_name = "_s" + s;
time_name = "_t" + t;
if ( z < 10 ){
		slice_name = "_z0" + z;
	} else {
		slice_name = "_z" + z;
	};
file_name = base_name + channel_name + location_name + time_name + slice_name + ".tif";
video_name = base_name + channel_name + location_name + slice_name  + ".AVI";

// folders definition
path_sorted_location = path_sorted + "location_" + s + "/";
path_sorted_slice = path_sorted_location + "slice_" + z + "/";
path_sorted_wavelength = path_sorted_slice + "wavelength_"+ w + channel + "/";
path_videos_location = path_videos + "location_" + s + "/";

//sanity check
//print(path_videos_location);
//print(File.exists(path_videos_location));

// print to make sure first file is found
print(path_sorted_wavelength + file_name);
print(File.exists(path_sorted_wavelength + file_name));

// loop over location directories
while (File.exists(path_sorted_location)) {
	// check for the output video folder, if it doesnt exist make it 
	if (File.exists(path_videos_location)==0) {
		File.makeDirectory(path_videos_location);
	};
	//print(s);
	//print(path_sorted_slice + file_name);
		
	//print(File.exists(path_sorted_slice));
	//print(File.exists(path_videos_location));
	//print(path_sorted_slice);
	//print(path_videos_location);
	
	// loop over slice folders 
    while (File.exists(path_sorted_slice)) {
		
		// loop over wavelength directories
		while (File.exists(path_sorted_wavelength)) {
			
			// check the first file target for video exists, basically if folder is empty
			if (File.exists(path_sorted_wavelength + file_name)){
				
				//print(path_sorted_wavelength + file_name);
				//print(File.exists(path_sorted_wavelength + file_name));
				
				// check the video doesn't already exist
				//if (File.exists(path_videos_location + video_name)==0){
				
				//run("Image Sequence...", "select=" + path_sorted_wavelength + " dir=" + path_sorted_wavelength + " sort use");
				run("Image Sequence...", " dir=" + path_sorted_wavelength + " sort use");
				
				//run("Duplicate...", "title=[temp] duplicate");
				//selectWindow("wavelength_" + w + channel);
				//close();
				//selectWindow("temp");
				run("AVI... ", "compression=JPEG frame=" + framerate + " save=[" + path_videos_location + video_name + "]");
				close();
			
				//};
			};
			//print(w);
			
			// increment the wavelength
			w = w + 1;
			channel = "FITC";
			channel_name = "_w" + w + channel + " Camera";
			// update file name
			file_name = base_name + channel_name + location_name + time_name + slice_name + ".tif";
			video_name = base_name + channel_name + location_name + slice_name  + ".AVI";
			
			// update folder location
			path_sorted_location = path_sorted + "location_" + s + "/";
			path_sorted_slice = path_sorted_location + "slice_" + z + "/";
			path_sorted_wavelength = path_sorted_slice + "wavelength_"+ w + channel + "/";
		
		};
			
		//reset wavelength
		w = 1;
		channel = "BF";
		channel_name = "_w" + w + channel + " Camera";
		// increment slice
		z = z + 1;
		if ( z < 10 ){
			slice_name = "_z0" + z;
		} else {
			slice_name = "_z" + z;
		};
		// update file name
		file_name = base_name + channel_name + location_name + time_name + slice_name + ".tif";
		video_name = base_name + channel_name + location_name + slice_name  + ".AVI";
			
		// update folder location
		path_sorted_location = path_sorted + "location_" + s + "/";
		path_sorted_slice = path_sorted_location + "slice_" + z + "/";
		path_sorted_wavelength = path_sorted_slice + "wavelength_"+ w + channel + "/";
			
	};
			
	//reset slice
	z = 1;
	if ( z < 10 ){
		slice_name = "_z0" + z;
	} else {
		slice_name = "_z" + z;
	};
	// increment location
	// update location 
	s = s + 1;
	location_name = "_s" + s;
	//print(location_name);

	// update file name
	file_name = base_name + channel_name + location_name + time_name + slice_name + ".tif";
	video_name = base_name + channel_name + location_name + slice_name  + ".AVI";
		
	// update folder location
	path_sorted_location = path_sorted + "location_" + s + "/";
	path_sorted_slice = path_sorted_location + "slice_" + z + "/";
	path_sorted_wavelength = path_sorted_slice + "wavelength_"+ w + channel + "/";
	path_videos_location = path_videos + "location_" + s + "/";
	//print(path_sorted_wavelength)
	//print(path_sorted_location)

};

//print("done");
// this will make all the pop-ups show up again
setBatchMode(false);
