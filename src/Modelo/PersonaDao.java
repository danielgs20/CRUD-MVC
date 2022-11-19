/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author jeferson.agudelo
 */
public class PersonaDao {
    
    Conexion conectar = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    
    public List listar()
    {
        List<Persona>datos=new ArrayList<>();
        String sql=("select * from Persona");
        try{
            con=conectar.getConnection();
            ps = con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                Persona p = new Persona();
                p.setId(rs.getInt(1));
                p.setNombres(rs.getString(2));
                p.setCorreo(rs.getString(3));
                p.setTelefono(rs.getString(4));
                datos.add(p);
            }
            
        } catch(Exception e){
            
        }
            
        return datos;
    }
    
    public int agregar(Persona per) {  
        int r=0;
        String sql="insert into persona(Nombres,Correo,Telefono)values(?,?,?)";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);            
            ps.setString(1,per.getNombres());
            ps.setString(2,per.getCorreo());
            ps.setString(3,per.getTelefono());
            r=ps.executeUpdate();    
            if(r==1){
                return 1;
            }
            else{
                return 0;
            }
        } catch (Exception e) {
        }  
        return r;
    }
    public int Actualizar(Persona per) {  
        int r=0;
        String sql="update persona set Nombres=?,Correo=?,Telefono=? where Id=?";        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);            
            ps.setString(1,per.getNombres());
            ps.setString(2,per.getCorreo());
            ps.setString(3,per.getTelefono());
            ps.setInt(4,per.getId());
            r=ps.executeUpdate();    
            if(r==1){
                return 1;
            }
            else{
                return 0;
            }
        } catch (Exception e) {
        }  
        return r;
    }
    public int Delete(int id){
        int r=0;
        String sql="delete from persona where Id="+id;
        try {
            con=conectar.getConnection();
            ps=con.prepareStatement(sql);
            r= ps.executeUpdate();
        } catch (Exception e) {
        }
        return r;
    }
    
    
}
