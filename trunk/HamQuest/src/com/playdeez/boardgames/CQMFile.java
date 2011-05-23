package com.playdeez.boardgames;
import java.io.*;

public class CQMFile {
	private byte[] buffer;
	public CQMFile(byte width,byte height)
	{
		buffer = new byte[2+width*height];
		buffer[0]=width;
		buffer[1]=height;
	}
	public CQMFile(CQMFile copyFrom)
	{
		buffer = new byte[2+copyFrom.getWidth()*copyFrom.getHeight()];
		buffer[0]=copyFrom.getWidth();
		buffer[1]=copyFrom.getHeight();
		for(byte x=0;x<getWidth();++x){
			for(byte y=0;y<getHeight();++y){
				setCellValue(x,y,copyFrom.getCellValue(x, y));
			}
		}
	}
	public byte getWidth()
	{
		return buffer[0];
	}
	public byte getHeight()
	{
		return buffer[1];
	}
	public byte getCellValue(byte x,byte y)
	{
		if(x<getWidth() && y<getHeight())
		{
			return buffer[2+x+y*getWidth()];
		}
		else
		{
			return 0;
		}
	}
	public void setCellValue(byte x,byte y,byte cellValue)
	{
		if(x<getWidth() && y<getHeight())
		{
			buffer[2+x+y*getWidth()]=cellValue;
		}
	}
	public void blend(CQMFile overlay, byte offsetX, byte offsetY, byte transparent)
	{
		for(byte x=0;x<overlay.getWidth();++x)
		{
			for(byte y=0;y<overlay.getHeight();++y)
			{
				if(overlay.getCellValue(x, y)!=transparent)
				{
					setCellValue((byte)(x+offsetX),(byte)(y+offsetY),overlay.getCellValue(x, y));
				}
			}
		}
	}
	public void toStream(OutputStream writer) throws IOException
	{
		writer.write(buffer, 0, buffer.length);
	}
}
