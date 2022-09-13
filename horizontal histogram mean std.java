
// define our function to export array

function array_to_string(a,label){
	nsize = a.length;
	str = label;
	for (i=0; i<nsize ; i++){
		str = str + " , " + toString(a[i]);
	};
	return str;
}

function export_array_horizontal(a,label){
	str = array_to_string(a,label);
	
	// print out the string to the file
	print(f,str);
}

function export_arrays_horizontal(a,alabel,b,blabel){
	// get the 2 arrays into strings first
	astr = array_to_string(a,alabel);
	bstr = array_to_string(b,blabel);
	
	// combine the 2 strings 
	str = astr + " , " + bstr;
	
	// print out the string to the file
	print(f,str);
}

// load the images

path_in = "C:/Users/jcv5ff/Desktop/Pool/";
path_out = "C:/Users/jcv5ff/Desktop/Well/";
name = "mean_std";


//run("Image Sequence...", "select=[" + path_in + "] dir=[" + path_in + "] sort use");
run("Image Sequence...", dir=[" + path_in + "] sort use");


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
//export the time data
export_arrays_horizontal(time_array,"time",time_array,"time");
// export the actual data
export_arrays_horizontal(mean_array,"mean",std_array,"stdev");
File.close(f);

// print the arrays to screen
//Array.print(mean_array);
//Array.print(std_array);


