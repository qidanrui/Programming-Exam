
// ChessBoardDlg.h : 头文件
//

#pragma once


// CChessBoardDlg 对话框
class CChessBoardDlg : public CDialogEx
{
// 构造
public:
	CChessBoardDlg(CWnd* pParent = NULL);	// 标准构造函数

// 对话框数据
	enum { IDD = IDD_CHESSBOARD_DIALOG };
	enum BlankState{BLACK,WHITE,BLANK};
	BlankState blank8x8[8][8];
	BlankState currChess;
	int blackNum;
	int whiteNum;
	bool flag;

	void DrawChessBoard();
	void DrawChess();
	bool CanPlace(int x, int y);
	bool IsOver(int x, int y);

	protected:
	virtual void DoDataExchange(CDataExchange* pDX);	// DDX/DDV 支持


// 实现
protected:
	HICON m_hIcon;

	// 生成的消息映射函数
	virtual BOOL OnInitDialog();
	afx_msg void OnSysCommand(UINT nID, LPARAM lParam);
	afx_msg void OnPaint();
	afx_msg HCURSOR OnQueryDragIcon();
	DECLARE_MESSAGE_MAP()
public:
	afx_msg void OnLButtonDown(UINT nFlags, CPoint point);
	afx_msg void OnRButtonDown(UINT nFlags, CPoint point);
	afx_msg void OnBnClickedWhite();
	afx_msg void OnBnClickedBlack();
};
