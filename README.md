# Virtual-File-System
A virtual file system with a root directory called "root" all the files and folders will be stored under it.  
The disk size consists of N blocks and each block size is 1 KB.  
Simulating allocation and de-allocation of files and folders using different allocation techniques.  
**Different allocation techniques:**
*  Contiguous Allocation (Using Best Fit allocation) 
*  Indexed Allocation
*  Linked Allocation

***Command	Summary***  
*CreateFile root/file.txt 100  
This command used to create file named “file.txt” with 100 KB size under the path “root”*  
**Pre-requests:**  
1-The path is already exist
2-No file with the same name is already created under this path
3-Enough space exists  
*CreateFolder root/folder1*  
This command is used to create a new folder named “folder1” under the path “root”  
**Pre-requests:**  
1-The path is already exist
2-No folder with the same name is already created under this path.  
*DeleteFile root/folder1/file.txt*  
This command used to delete file named “file.txt” form the path "root/folder1".  
Any blocks allocated by this file should be de-allocated.  
**Pre-requests:**  
1-The file is already exist under the path specified  
**DeleteFolder root/folder1**  
This command used to delete folder named “folder1” form the path "root".  
All files and subdirectories of this folder will also be deleted.  
**Pre-requests:**  
1-The folder is already exist under the path specified  
**DisplayDiskStatus**  
This command used to display the status of your Driver the status should contain the following information:  
*  Empty space
*  Allocated space
*  Empty Blocks in the Disk
*  Allocated  Blocks in the Disk    
  
**DisplayDiskStructure**  
This command will display the files and folders in your system file in a tree structure  
