1.解压VBA宏插件（含安装说明）.rar, 运行一键启用宏.exe, 解锁wps的宏功能, 本文档记录时wps版本为12.1.0.24034
2.转为浮动图片
3.alt+F11, 插入->模块
4.编写宏代码
```text
Sub ResizeAndCenterPicturesInColumnG_WPS_NoFloating_AutoWidth()
Dim ws As Worksheet
Dim shp As Shape
Dim targetCol As String
Dim picSize As Single
Dim c As Range
Dim totalWidth As Double

    ' --- 设置目标列和图片尺寸, 最好不要超过240, 单元格大小有限制 ---
    targetCol = "G"
    picSize = 120
    
    Set ws = ActiveSheet
    
    ' 遍历图片
    For Each shp In ws.Shapes
        ' 是否在 G 列
        If Not Intersect(shp.TopLeftCell, ws.Columns(targetCol)) Is Nothing Then
            
            ' --- 设置图片大小 ---
            shp.LockAspectRatio = msoFalse
            shp.Width = picSize
            shp.Height = picSize
            
            ' --- 自动调整行高 ---
            With shp.TopLeftCell
                If .RowHeight < picSize Then
                    .RowHeight = picSize + 4
                End If
            End With
            
            ' --- 自动调整列宽 ---
            Set c = shp.TopLeftCell
            totalWidth = c.Width ' 单元格当前宽度（单位：像素）
            If totalWidth < picSize Then
                ' 列宽递增直到满足图片宽度
                Do While c.Width < picSize
                    ws.Columns(c.Column).ColumnWidth = ws.Columns(c.Column).ColumnWidth + 1
                Loop
            End If
            
            ' --- 居中图片 ---
            With shp
                .Left = shp.TopLeftCell.Left + (shp.TopLeftCell.Width - shp.Width) / 2
                .Top = shp.TopLeftCell.Top + (shp.TopLeftCell.Height - shp.Height) / 2
            End With
            
        End If
    Next shp
    
    MsgBox "完成：G 列图片放大、自动居中，行高和列宽已自适应！"
End Sub
```
5.执行宏,alt+F8