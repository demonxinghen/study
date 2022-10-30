assume cs:abc
abc segment
    mov ax,2
    add ax,ax
    add ax,ax

    mov ax,4c00H
    int 21H ; 先固定写法,不必理解
abc ends
end