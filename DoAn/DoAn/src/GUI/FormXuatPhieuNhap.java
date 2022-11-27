package GUI;
import DTO.PhieuNhapDTO;
import DTO.NhaCungCapDTO;
import DTO.NhanVienDTO;
import java.awt.*;
import javax.swing.*;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Vector;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.awt.event.ActionEvent;
public class FormXuatPhieuNhap {
	protected JFrame Frame;
	private PhieuNhapDTO PN;
	private NhanVienDTO UserLogins;
	private NhaCungCapDTO NCC;
	private JEditorPane txtHoaDon;
	private ArrayList<Vector> dsSPMua;
	
	public FormXuatPhieuNhap(PhieuNhapDTO PhieuNhap,ArrayList<Vector> dsGioHang,NhanVienDTO UserLogin,NhaCungCapDTO NhaCungCap) {
		PN = PhieuNhap;
		UserLogins = UserLogin;
		NCC = NhaCungCap;
		dsSPMua = dsGioHang;
		initComponents();
	}
	
	public void initComponents() {
		Frame = new JFrame();
		Frame.setSize(500, 700);
		Frame.setLocationRelativeTo(null);
		Frame.setResizable(false);
		Frame.setUndecorated(true);
		Frame.getContentPane().setBackground(new Color(247, 248, 252));
		Frame.getContentPane().setLayout(null);
		
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        DecimalFormat dcf = new DecimalFormat("###,### VND");
        String hd = "<style> "
                + "table {"
                + "border: 1px solid;"
                + "border-bottom: none"
                + "}"
                + "tr {"
                + "border-bottom: 1px solid;"
                + "}"
                + "td {"
                + "padding: 8px;"
                + "} "
                + "th {"
                + "font-size:16pt"
                + "}"
                + "</style>";
        hd += "<h1 style='text-align:center;'>PHIẾU NHẬP</h1>";
        hd += "Phiếu nhập: #" + PN.getPhieuNhap_ID() + "<br/>";
        hd += "Nhân Viên: " + UserLogins.getNhanVien_Name() + "<br/>";
        hd += "Ngày Lập: " + dtf.format(now) + "<br/>";
        hd += "Nhà Cung Cấp: " + NCC.getNhaCungCap_Name() + "<br/>";
        hd += "<div style='text-align:center;'>==========================================</div><br/>";
        hd += "<div style='text-align:center'>";
        hd += "<table style='max-width:100%'>";
        hd += "<tr>"
                + "<th>Mã SP</th>"
                + "<th>Tên SP</th>"
                + "<th>Đơn giá</th>"
                + "<th>Số lượng</th>"
                + "<th>Thành tiền</th>"
                + "</tr>";
        for (Vector vec : dsSPMua) {
            hd += "<tr>";
            hd += "<td style='text-align:center;'>" + vec.get(0) + "</td>";
            hd += "<td style='text-align:left;'>" + vec.get(1) + "</td>";
            hd += "<td style='text-align:center;'>" + vec.get(2) + "</td>";
            hd += "<td style='text-align:center;'>" + vec.get(3) + "</td>";
            hd += "<td style='text-align:center;'>" + vec.get(4) + "</td>";
            hd += "</tr>";
        }
        hd += "<tr>";
        hd += "<td style='text-align:center;'>" + "</td>";
        hd += "<td style='text-align:left;'>" + "</td>";
        hd += "<td style='text-align:center;'>" + "</td>";
        hd += "<td style='text-align:center;font-weight:bold'>Tổng cộng</td>";
        hd += "<td style='text-align:center;'>" + dcf.format(PN.getPhieuNhap_Total()) + "</td>";
        hd += "</tr>";
        hd += "</table>";
        hd += "</div>";
        hd += "<div style='text-align:center;'>==========================================</div><br/>";
        
        txtHoaDon = new JEditorPane();
        txtHoaDon.setBounds(10, 11, 480, 627);
        txtHoaDon.setEditable(false);
        txtHoaDon.setContentType("text/html");
        txtHoaDon.setText(hd);
		JScrollPane scrollPane = new JScrollPane(txtHoaDon);
		scrollPane.setBounds(10, 11, 480, 627);
		Frame.getContentPane().add(scrollPane);
		
		JButton btnInHoaDon = new JButton("In hóa đơn");
		btnInHoaDon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InHoaDon();
			}
		});
		btnInHoaDon.setFont(new Font("Arial", Font.BOLD, 18));
		btnInHoaDon.setBounds(180, 649, 150, 40);
		Frame.getContentPane().add(btnInHoaDon);
    }
	private void InHoaDon() {
		try {
			txtHoaDon.print();
			Frame.dispose();
		} catch (PrinterException ex) {
			System.out.println(ex);
		}
	}
}
