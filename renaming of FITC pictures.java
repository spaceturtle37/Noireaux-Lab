
// this only works for nice naming conventions , careful bout ordering

path_out = "C:/Users/Jcv5ff/Desktop/Well/";

temp_FITC_dir = path_out + "temp/";
names_dir = path_out + "enhanced_FITC/";
output_folder = path_out + "greened_FITC/";

target_list = getFileList(temp_FITC_dir);
names_list = getFileList(names_dir);

sorted_target_list = Array.sort(target_list);
sorted_names_list = Array.sort(names_list);

nfiles = target_list.length;
//nfile = names_list.length;

//Array.print(target_list);
//Array.print(names_list);

for (i = 0; i < nfiles; i++){
        //name = names_dir + names_list[i];
		//target = temp_FITC_dir + target_list[i];
		print(target_list[i]);
		print(sorted_target_list[i]);
		print(names_list[i]);
		print(sorted_names_list[i]);
		File.copy(temp_FITC_dir + target_list[i], output_folder + names_list[i]);
		
};