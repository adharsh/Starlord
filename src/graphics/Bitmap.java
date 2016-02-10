package graphics;

public class Bitmap {
	
	private byte[] map;
	private int width;
	private int height;
	
	public Bitmap(int width, int height){
		this.width = width;
		this.height = height;
		
		map = new byte[ width * height * 4];
	}
	
	
	public int height(){
		 return height;
	}
	
	
	public int width(){
		 return width;
	}
	
	
	public void clear(byte color){
		
		for(int i = 0; i < map.length; i++)
			map[i] = color;
		
	}
	
	public void setPixel(int x, int y, byte a, byte r, byte g, byte b){
		
		int index = (x + y * width) * 4;
		map[index] = a;
		map[index + 1] = r;
		map[index + 2] = g;
		map[index + 3] = b;
		
	}
	
	
	public void map(byte[] src){
		
		for(int i = 0; i < width*height; i++){
			
			map[i*4] = src[i];
			map[i*4 + 1] = src[i*3 + 1];
			map[i*4 + 2] = src[i*3 + 2];
			map[i*4 + 3] = src[i*3 + 3];
			
		}
		
		
	}
	
}
