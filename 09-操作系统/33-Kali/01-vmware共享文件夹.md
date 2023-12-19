1.在宿主机配置好共享文件夹，且在vm配置好共享后，需要在Linux里挂载
```shell
sudo vmhgfs-fuse .host:/vmshare /mnt/shared -o allow_other
# 如果不行试试这个,sudo mount -t fuse.vmhgfs-fuse .host:/vmshare /mnt/shared -o allow_other
# /vmshare是在vm里配置的共享文件夹 /mnt/shared是Linux里挂载的目录，需要提前创建 -o allow_other表示普通用户也可以使用共享文件夹
```