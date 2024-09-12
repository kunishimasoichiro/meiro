import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
 
public class Maze implements KeyListener{
 
	    private int pointX; 
	    private int pointY;
	    private int width; 
	    private int height;
	  
	    private byte[][] map; 
	    public Maze(int w, int h) {
	    	width = w;
	        height = h;
	        if (w % 2 != 0 && h % 2 != 0 && 5 <= w && 5 <= h) {
	            map = new byte[width][height];
	            make();
	        } else {
	            System.out.println("縦・横共に5以上の奇数で作成してください。");
	        }
	    }
 
 



		int randomPos(int muki) { 
	        int result = 1 + 2 * (int) Math.floor((Math.random() * (muki - 1)) / 2);
	        return result;
	    }
 
	    private void make() { 
 
	        pointX = randomPos(width);
	        pointY = randomPos(height);
 
	        for (int y = 0; y < height; y++) { 
	            for (int x = 0; x < width; x++) {
	                map[x][y] = 1;
	            }
	        }
	        map[pointX][pointY] = 0;
	        dig();
 
	    }
 
	    private void dig() {
	        if (isAbleContinueDig() && map[pointX][pointY] == 0) {
	            map[pointX][pointY] = 0;
	            int direction = (int) Math.floor(Math.random() * 4);
	            switch (direction) {
	            case 0:
	                if (pointY != 1) {
	                    if (map[pointX][pointY - 2] == 1) {
	                        map[pointX][pointY - 1] = 0;
	                        pointY -= 2;
	                        break;
	                    }
	                }
	            case 1:
	                if (pointY != height - 2) {
	                    if (map[pointX][pointY + 2] == 1) {
	                        map[pointX][pointY + 1] = 0;
	                        pointY += 2;
	                        break;
	                    }
	                }
	            case 2:
	                if (pointX != 1) {
	                    if (map[pointX - 2][pointY] == 1) {
	                        map[pointX - 1][pointY] = 0;
	                        pointX -= 2;
	                        break;
	                    }
	                }
	            case 3:
	                if (pointX != width - 2) {
	                    if (map[pointX + 2][pointY] == 1) {
	                        map[pointX + 1][pointY] = 0;
	                        pointX += 2;
	                        break;
	                    }
	                }
	            }
	            map[pointX][pointY] = 0;
	            dig();
	        } else if (isAbleDig()) {
	            pointX = randomPos(width);
	            pointY = randomPos(height);
	            dig();
	        }
 
	    }
 
	    private boolean isAbleDig() { 
	        boolean result;
	        int cnt = 0;
	        for (int y = 0; y < height; y++) {
	            for (int x = 0; x < width; x++) {
	                if (x % 2 != 0 && y % 2 != 0) {
 
	                    if (map[x][y] != 0) {
	                        cnt++;
	                    }
	                }
	            }
	        }
	        if (cnt == 0) {
	            result = false;
	        } else {
	            result = true;
	        }
	        return result;
	    }
 
	    private boolean isAbleContinueDig() {
 
	        if (pointY != 1) {
	            if (map[pointX][pointY - 2] == 1) {
	                return true;
	            }
	        }
	        if (pointY != height - 2) {
	            if (map[pointX][pointY + 2] == 1) {
	                return true;
	            }
	        }
	        if (pointX != 1) {
	            if (map[pointX - 2][pointY] == 1) {
	                return true;
	            }
	        }
	        if (pointX != width - 2) {
	            if (map[pointX + 2][pointY] == 1) {
	                return true;
	            }
	        }
	        return false;
	    }
 
	    public void show() {
	        for (int y = 0; y < map[0].length; y++) {
	            System.out.println("");
	            for (int x = 0; x <map.length; x++) {
 
	                if (map[x][y] == 1) {
	                    System.out.print("##");
	                } else {
	                    System.out.print("  ");
	                }
	            }
	        }
	    }
	    public byte[][] getMaze() {
	        return map;
	    }
         
	   
	    public void keyPressed(KeyEvent e) {
	        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
	            System.out.println("スペースキーが押されました。迷路を再生成します。");
	            make();
	            isAbleDig();
	            isAbleContinueDig();
	            show();
	            getMaze();
	            MainMaze.spacePressed =true;
	        }
	    }
 
 
		@Override
		public void keyTyped(KeyEvent e) {
			// TODO 自動生成されたメソッド・スタブ
			
		}
 
 
		@Override
		public void keyReleased(KeyEvent e) {
			// TODO 自動生成されたメソッド・スタブ
			
		}
	}