package in4rows.model;

import in4rows.GridHelper;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static in4rows.GridHelper.*;

@RunWith(JUnit4.class)
public class TestGridHelper {

	@Test
	public void testGridHelper_01() {
		DummyGrid d = new DummyGrid(10, 20);

		Assert.assertEquals("height not ok", 10, d.getHeight());
		Assert.assertEquals("width not ok", 20, d.getWidth());

		Assert.assertEquals("not v(0,0) !", new BasicVertex(0, 0),
				firstInCol_ModeCol(d, 0));

		d.setDisk(0, 0, Disk.BLACK);
		Assert.assertEquals("not v(0,0) !", Disk.BLACK, d.getDisk(0, 0));
		Assert.assertEquals("not v(0,0) !", new BasicVertex(1, 0),
				firstInCol_ModeCol(d, 0));

		d.setDisk(0, 5, Disk.BLACK);
		Assert.assertEquals("not v(5,5) !", Disk.BLACK, d.getDisk(0, 5));
		Assert.assertEquals("not v(5,5) !", new BasicVertex(1, 5),
				firstInCol_ModeCol(d, 5));
	}

	@Test
	public void testGridHelper_02() {
		DummyGrid d = new DummyGrid(3, 3);
		d.setDisk(0, 0, Disk.BLACK);
		d.setDisk(1, 0, Disk.BLACK);
		d.setDisk(2, 0, Disk.BLACK);

		Assert.assertEquals("not v()", new BasicVertex(0, 1),
				firstInGame_ModeCol(d));

	}

	@Test
	public void testGridHelper_03() {
		DummyGrid d = new DummyGrid(3, 3);
		d.setDisk(0, 0, Disk.BLACK);
		d.setDisk(1, 0, Disk.BLACK);
		d.setDisk(2, 0, Disk.BLACK);
		d.setDisk(0, 2, Disk.BLACK);

		List<Vertex> list = new ArrayList<>(2);
		list.add(new BasicVertex(0, 1));
		list.add(new BasicVertex(1, 2));
		Assert.assertEquals("not v()", list, GridHelper.possibleSquares(d));

	}

	@Test
	public void testGridHelper_04() {
		DummyGrid d = new DummyGrid(10, 10);
		d.setDisk(6, 3, Disk.BLACK);
		d.setDisk(7, 3, Disk.BLACK);
		d.setDisk(8, 3, Disk.BLACK);
		d.setDisk(5, 3, Disk.BLACK);

		Vertex v = new BasicVertex(6, 3);
		int countVertical = 1 + countUp(d, v) + countDown(d, v);
		Assert.assertEquals("not 4", 4, countVertical);

		d.setDisk(8, 3, Disk.WHITE);
		countVertical = 1 + countUp(d, v) + countDown(d, v);
		Assert.assertEquals("not 3", 3, countVertical);
	}

	@Test
	public void testGridHelper_05() {
		DummyGrid d = new DummyGrid(10, 10);
		d.setDisk(6, 3, Disk.BLACK);
		d.setDisk(6, 4, Disk.BLACK);
		d.setDisk(6, 5, Disk.BLACK);
		d.setDisk(6, 2, Disk.BLACK);

		Vertex v = new BasicVertex(6, 3);
		int countVertical = 1 + countRight(d, v) + countLeft(d, v);
		Assert.assertEquals("not 4 !! ", 4, countVertical);

		d.setDisk(6, 5, Disk.WHITE);
		countVertical = 1 + countRight(d, v) + countLeft(d, v);
		Assert.assertEquals("not 3 !! ", 3, countVertical);
	}

	@Test
	public void testGridHelper_06() {
		DummyGrid d = new DummyGrid(10, 10);
		d.setDisk(6, 3, Disk.BLACK);
		d.setDisk(6, 4, Disk.BLACK);
		d.setDisk(6, 5, Disk.BLACK);
		d.setDisk(6, 2, Disk.BLACK);

		Vertex v = new BasicVertex(6, 3);
		int countVertical = 1 + countRight(d, v) + countLeft(d, v);
		Assert.assertEquals("not 4 !! ", 4, countVertical);

		d.setDisk(6, 5, Disk.WHITE);
		countVertical = 1 + countRight(d, v) + countLeft(d, v);
		Assert.assertEquals("not 3 !! ", 3, countVertical);
	}	

	@Test
	public void testGridHelper_07() {
		DummyGrid d = new DummyGrid(10, 10);
		d.setDisk(4, 1, Disk.BLACK);
		d.setDisk(5, 2, Disk.BLACK);
		d.setDisk(6, 3, Disk.BLACK);
		d.setDisk(3, 0, Disk.BLACK);

		Vertex v = new BasicVertex(4, 1);
		int countVertical = 1 + countDiagRight(d, v) + countDiagLeft(d, v);
		Assert.assertEquals("not 4 !! ", 4, countVertical);

		d.setDisk(3, 0, Disk.WHITE);
		countVertical = 1 + countDiagRight(d, v) + countDiagLeft(d, v);
		Assert.assertEquals("not 3 !! ", 3, countVertical);
	}		
	private class DummyGrid implements GameReadable {

		private Disk[][] grid;

		public DummyGrid(int row, int col) {
			grid = new Disk[row][col];
		}

		@Override
		public Disk getDisk(int row, int col) {
			return grid[row][col];
		}

		@Override
		public int getHeight() {
			return grid.length;
		}

		@Override
		public int getWidth() {
			return grid[0].length;
		}

		public void setDisk(int row, int col, Disk d) {
			grid[row][col] = d;
		}
	}

}
