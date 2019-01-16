
// 八皇后Dlg.h : 头文件
//

#pragma once
#include <vector>
using namespace std;

struct POSTION{
	int x;
	int y;
};

// C八皇后Dlg 对话框
class C八皇后Dlg : public CDialogEx
{
// 构造
public:
	C八皇后Dlg(CWnd* pParent = NULL);	// 标准构造函数

// 对话框数据
	enum { IDD = IDD_MY_DIALOG };
	enum CHESS{BLANK,BLACK,RED};
	CHESS chesses[8][8];
	int align;
	int width;
	int count;
	
	vector<POSTION> red_vec;
	
	void drawChessBoard();
	void drawChess();
	bool isOver();
	bool isLose(int x,int y);
	bool canPlace(int x,int y);
	

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
