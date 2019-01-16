
// CompetitionDlg.h : 头文件
//

#pragma once
#include "afxwin.h"


// CCompetitionDlg 对话框
class CCompetitionDlg : public CDialogEx
{
// 构造
public:
	CCompetitionDlg(CWnd* pParent = NULL);	// 标准构造函数

// 对话框数据
	enum { IDD = IDD_COMPETITION_DIALOG };

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
	CString Blue;
	afx_msg void OnLbnSelchangeList1();
	CString Red;
	CString Who;
	CButton Start;
	CButton Exit;
	afx_msg void OnEnChangeEdit2();
	afx_msg void OnBnClickedButton1();
	afx_msg void OnLButtonDown(UINT nFlags, CPoint point);
	afx_msg void OnEnChangeEdit3();
	public:
	bool blueOrRed;
	bool Begin;
	int judge[8][8];
	int numberRed[8][8];
	int numberBlue[8][8];
	int BlueScore;
	int RedScore;
	void text();
	bool Gameover();
	afx_msg void OnBnClickedButton2();
};
