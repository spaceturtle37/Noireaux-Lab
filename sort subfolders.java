//************************************************************************
// ********** Grouping unpacked images per location and slice ************
//************************************************************************

// this will make all the pop-ups not show up
setBatchMode(true);

// base name of the pictures
base_name = "EutM2nM_";

root_folder = "C:/Users/Jcv5ff/Desktop/";
path_unpack = root_folder + "Well/Kinetics/";
path_sorted = root_folder + "Well/Sorted/";
// look for output folder, if it doesnt exist make it
if (File.exists(path_sorted)==0) {
	File.makeDirectory(path_sorted);
};

// indices initialization
w = 1;
channel = "BF";
s = 1;
t = 1;
z = 1;

// define file name
channel_name = "_w" + w + channel + " Camera";
location_name = "_s" + s;
time_name = "_t" + t;
if ( z < 10 ){
		slice_name = "_z0" + z;
	} else {
		slice_name = "_z" + z;
	};
file_name = base_name + channel_name + location_name + time_name + slice_name + ".tif";


// folders definition
path_sorted_location = path_sorted + "location_" + s + "/";
path_sorted_slice = path_sorted_location + "slice_" + z + "/";
path_sorted_wavelength = path_sorted_slice + "wavelength_"+ w + channel + "/";

// print to make sure first file is found
print(path_unpack + file_name);
print(File.exists(path_unpack + file_name));

// the bulk of the sorting
while (File.exists(path_unpack + file_name)) { // loop over the different locations, while checking whether the picturs exist
	// sanity check + sanity cure
	if (File.exists(path_sorted_location)==0) {
		File.makeDirectory(path_sorted_location);
	};
	//print(s);
	//print(path_sorted_slice + file_name);
		
    while (File.exists(path_unpack + file_name)) { // loop over the different slices recorded at the s-th location, while checking whether the picturs exist
		// sanity check + sanity cure
		if (File.exists(path_sorted_slice)==0) {
			File.makeDirectory(path_sorted_slice);
		};
		//print(z);
		//print(path_sorted_slice + file_name);
			
		while (File.exists(path_unpack + file_name)) { // loop over the different wavelength at s-location and z-slice, while checking whether the picturs exist
			// sanity check + sanity cure
			if (File.exists(path_sorted_wavelength)==0) {
				File.makeDirectory(path_sorted_wavelength);
			};	
			//print(w);
			//print(channel);
			//print(path_sorted_wavelength + file_name);
		
			while (File.exists(path_unpack + file_name)) { // loop over the different time steps, at the z-th sice, recorded at the s-th location, while checking whether the picturs exist
				output_name = file_name;
				//print(path_unpack + file_name);
				//print(path_sorted_wavelength + output_name);
				File.rename(path_unpack + file_name, path_sorted_wavelength + output_name); // moving files from original pool
				////print(t);
				// increment time
				t = t + 1;
				time_name = "_t" + t;
				// update file name
				file_name = base_name + channel_name + location_name + time_name + slice_name + ".tif";
				
			};
			
			// reset time index
			t = 1;
			time_name = "_t" + t;
			// increment the wavelength
			w = w + 1;
			channel = "FITC";
			channel_name = "_w" + w + channel + " Camera";
			// update file name
			file_name = base_name + channel_name + location_name + time_name + slice_name + ".tif";
			
			// update folder location
			path_sorted_location = path_sorted + "location_" + s + "/";
			path_sorted_slice = path_sorted_location + "slice_" + z + "/";
			path_sorted_wavelength = path_sorted_slice + "wavelength_"+ w + channel + "/";
			
		};	
			
		// reset wavelength
		w = 1;
		channel = "BF";
		channel_name = "_w" + w + channel + " Camera";
		// update slice 
		z = z + 1;
		if ( z < 10 ){
			slice_name = "_z0" + z;
		} else {
			slice_name = "_z" + z;
		};
		// update file name
		file_name = base_name + channel_name + location_name + time_name + slice_name + ".tif";
		
		// update folder location
		path_sorted_location = path_sorted + "location_" + s + "/";
		path_sorted_slice = path_sorted_location + "slice_" + z + "/";
		path_sorted_wavelength = path_sorted_slice + "wavelength_"+ w + channel + "/";
		
	};
	
	// reset slice 
	z = 1;
	if ( z < 10 ){
		slice_name = "_z0" + z;
	} else {
		slice_name = "_z" + z;
	};
	// update location 
	s = s + 1;
	location_name = "_s" + s;
	// update file name
	file_name = base_name + channel_name + location_name + time_name + slice_name + ".tif";
	
	// update folder location
	path_sorted_location = path_sorted + "location_" + s + "/";
	path_sorted_slice = path_sorted_location + "slice_" + z + "/";
	path_sorted_wavelength = path_sorted_slice + "wavelength_"+ w + channel + "/";
	
};

//print("done");
// this will make all the pop-ups show up again
setBatchMode(false);
