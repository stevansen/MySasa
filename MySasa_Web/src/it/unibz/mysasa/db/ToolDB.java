package it.unibz.mysasa.db;

import it.unibz.mysasa.domain.Fermata;
import it.unibz.mysasa.domain.Line;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Vector;

public class ToolDB{

	public void createDB() {
		String q1 = null;
		q1 = "CREATE TABLE linea( ";
		q1 += "   linr    INT,";
		q1 += "   name    CHAR(50),";
		q1 += "   uri     TEXT";
		q1 += ");";
		String q2 = null;
		q2 = "CREATE TABLE fermata( ";
		q2 += "  ortnr  INT   NOT NULL, ";
		q2 += "  lat    REAL, ";
		q2 += "  lon    REAL  ";
		q2 += ");";
		String q3 = null;
		q3 = "CREATE TABLE percorso( ";
		q3 += "  linr  INT,";
		q3 += "  var   INT,";
		q3 += "  ortnr INT ";
		q3 += ");";
		Postgres pg = new Postgres();
		try {
			Statement stmt = pg.getCon().createStatement();
			stmt.execute("DROP TABLE IF EXISTS linea");
			stmt.execute(q1);
			stmt.execute("DROP TABLE IF EXISTS fermata");
			stmt.execute(q2);
			stmt.execute("DROP TABLE IF EXISTS percorso");
			stmt.execute(q3);
			stmt.close();
			pg.getCon().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void clean() {
		String q1 = "DELETE FROM linea";
		String q2 = "DELETE FROM fermata";
		String q3 = "DELETE FROM percorso";
		Postgres pg = new Postgres();
		try {
			Statement stmt = pg.getCon().createStatement();
			stmt.execute(q1);
			stmt.execute(q2);
			stmt.execute(q3);
			stmt.close();
			pg.getCon().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void insertLine(Integer nr, String line, String uri) {
		String q = "INSERT INTO linea (linr, name, uri) VALUES( ?, ?, ? );";
		Postgres pg = new Postgres();
		try {
			PreparedStatement pstmt = pg.getCon().prepareStatement(q);
			pstmt.setInt(1, nr);
			pstmt.setString(2, line);
			pstmt.setString(3, uri);
			pstmt.execute();
			pstmt.close();
			pg.getCon().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private Line buildLine(ResultSet rs) throws Exception {
		Line l = new Line();
		l.setLineNr(rs.getInt("linr"));
		l.setLineName(rs.getString("name"));
		l.setURI(rs.getString("uri"));
		return l;
	}
	
	public List<Line> getLine(String name){
		List<Line> ret = new Vector<Line>();
		String q = "SELECT * FROM linea WHERE name = ?;";
		Postgres pg = new Postgres();
		try {
			PreparedStatement pstmt = pg.getCon().prepareStatement(q);
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				ret.add(buildLine(rs));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}
	
	public void insertStation(Integer station, Double lon, Double lat) {
		String q = "INSERT INTO fermata (ortnr, lon, lat) VALUES( ?,?,?);";
		Postgres pg = new Postgres();
		try {
			PreparedStatement pstmt = pg.getCon().prepareStatement(q);
			pstmt.setInt(1, station);
			pstmt.setDouble(2, lon);
			pstmt.setDouble(3, lat);
			pstmt.execute();
			pstmt.close();
			pg.getCon().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private Fermata buildFermata(ResultSet rs) throws Exception {
		Fermata f = new Fermata();
		f.setOrtNr(rs.getInt("ortnr"));
		f.setLat(rs.getDouble("lat"));
		f.setLon(rs.getDouble("lon"));
		return f;
	}
	
	public List<Fermata> getFermate() {
		List<Fermata> ret = new Vector<Fermata>();
		String q = "SELECT * FROM fermata;";
		Postgres pg = new Postgres();
		try {
			Statement stmt = pg.getCon().createStatement();
			ResultSet rs = stmt.executeQuery(q);
			while (rs.next()) {
				ret.add(buildFermata(rs));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}
	
	public Fermata getFermataId(Integer id){
		Fermata ret = null;
		String q = "SELECT * FROM fermata WHERE ortnr = ?;";
		Postgres pg = new Postgres();
		try {
			PreparedStatement pstmt = pg.getCon().prepareStatement(q);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				ret = buildFermata(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}
	
	public List<Fermata> getFermate(Integer line) {
		List<Fermata> ret = new Vector<Fermata>();
		String q = "";
		q += "SELECT f.* ";
		q += "  FROM fermata f, percorso p ";
		q += " WHERE f.ortnr = p.ortnr ";
		q += "   AND p.linr = ?";
		Postgres pg = new Postgres();
		try {
			PreparedStatement pstmt = pg.getCon().prepareStatement(q);
			pstmt.setInt(1, line);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				ret.add(buildFermata(rs));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}
	
	public List<Fermata> getFermate(Integer line, Integer var){
		List<Fermata> ret = new Vector<Fermata>();
		String q = "";
		q += "SELECT f.* ";
		q += "  FROM fermata f, percorso p ";
		q += " WHERE f.ortnr = p.ortnr ";
		q += "   AND p.linr = ? ";
		q += "   AND p.var = ?";
		Postgres pg = new Postgres();
		try {
			PreparedStatement pstmt = pg.getCon().prepareStatement(q);
			pstmt.setInt(1, line);
			pstmt.setInt(2, var);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Fermata f = buildFermata(rs);
				ret.add(f);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}
	
	public void insertPercorso(Integer line, Integer var, Integer stat) {
		String q = "INSERT INTO percorso (linr, var, ortnr) VALUES(?,?,?);";
		Postgres pg = new Postgres();
		try {
			PreparedStatement pstmt = pg.getCon().prepareStatement(q);
			pstmt.setInt(1, line);
			pstmt.setInt(2, var);
			pstmt.setInt(3, stat);
			pstmt.execute();
			pstmt.close();
			pg.getCon().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}