
// define our function to export array

function array_to_csv_string(a,label){
	nsize = a.length;
	str = label;
	for (i=0; i<nsize ; i++){
		str = str + " , " + toString(a[i]);
	};
	return str;
}

function export_array_horizontal(a,label){
	str = array_to_csv_string(a,label);
	
	// print out the string to the file
	print(f,str);
}

function export_arrays_horizontal(a,alabel,b,blabel){
	// get the 2 arrays into strings first
	astr = array_to_csv_string(a,alabel);
	bstr = array_to_csv_string(b,blabel);
	
	// combine the 2 strings 
	str = astr + " , " + bstr;
	
	// print out the string to the file
	print(f,str);
}

function export_arrays_vertical(time,tlabel,a,alabel,b,blabel){
	// print the row of labels
	print(f, tlabel + " , " + alabel + " , " + tlabel + " , " + blabel );
	
	// print data columns
	nsize = time.length;
	for (i = 0; i < nsize; i++){
		str = toString(time[i]) + " , " + toString(a[i]) + " , " + toString(time[i]) + " , " + toString(b[i]);
		print(f,str);
	};
}

// load the images

root_folder = "C:/Users/Jcv5ff/Desktop/";
//path_in = root_folder + "Well/" + getArgument() + "/";
path_in = root_folder + "Well/";
path_out = root_folder + "Well/Histograms/";
//name = getArgument();
filter = getArgument();
//filter = "(.*" + getArgument() + ".*)";
//name = filter;
//name = "mean stdev";
//name = getArgument();

// look for output folder, if it doesnt exist make it
if (File.exists(path_out)==0) {
	File.makeDirectory(path_out);
};


//run("Image Sequence...", " dir=" + path_in + " sort use");
run("Image Sequence...", "dir=" + path_in + " filter=" + filter + " sort use ");
// get name from first slice
name = getInfo("slice.label");
//print(name);


npics = nSlices();
// start empty arrays
time_array = newArray(0);
mean_array = newArray(0);
std_array = newArray(0);

// 10 min = 10/60 hrs 
dt_step = 10 ;
dt_step = dt_step / 60 ;

for (i = 0; i < npics; i++){
	// update the time 
	time = i * dt_step;
	time_array = Array.concat(time_array,time);
	
	// slices start counting at i = 1
	slice_number = i+1;
	setSlice(slice_number);
	
	getRawStatistics(n, mean, min, max, std, hist);
	mean_array = Array.concat(mean_array,mean);
	std_array = Array.concat(std_array,std);
}

// close image sequence
close();

// bring up prompt to choose how to save the file
//f = File.open("");

// save the array info to chosen file name without prompt
f = File.open(path_out + name + ".csv");
//export the time data as labels 
//export_arrays_horizontal(time_array,"time",time_array,"time");
// export the actual data
//export_arrays_horizontal(mean_array,"mean",std_array,"stdev");

export_arrays_vertical(time_array,"time [hr]",mean_array,"mean",std_array,"stdev");
File.close(f);

// print the arrays to screen
//Array.print(mean_array);
//Array.print(std_array);


