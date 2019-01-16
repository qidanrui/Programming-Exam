
// PingTuDlg.h : 头文件
//

#pragma once
#include <vector>
using namespace std;

// CPingTuDlg 对话框
class CPingTuDlg : public CDialogEx
{
// 构造
public:
	CPingTuDlg(CWnd* pParent = NULL);	// 标准构造函数

// 对话框数据
	enum { IDD = IDD_PINGTU_DIALOG };
	int align;
	int width;
	
	int chesses[4][4];
	int empty_x;
	int empty_y;
	
	void drawChessBoard();
	void drawChess();
	void initBoard();
	bool ifChangeLeft(int x,int y);
	bool ifChangeRight(int x,int y);
	bool ifChangeUp(int x,int y);
	bool ifChangeDown(int x,int y);
	void change(int x,int y);
	bool isOver();
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
};
