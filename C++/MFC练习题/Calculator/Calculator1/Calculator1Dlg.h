
// Calculator1Dlg.h : 头文件
//

#pragma once


// CCalculator1Dlg 对话框
class CCalculator1Dlg : public CDialogEx
{
// 构造
public:
	CCalculator1Dlg(CWnd* pParent = NULL);	// 标准构造函数

// 对话框数据
	enum { IDD = IDD_CALCULATOR1_DIALOG };

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
	afx_msg void OnBnClickedOne();
	afx_msg void OnBnClickedTwo();
	afx_msg void OnBnClickedThree();
	afx_msg void OnBnClickedFour();
	afx_msg void OnBnClickedFive();
	afx_msg void OnBnClickedSex();
	afx_msg void OnBnClickedSeven();
	afx_msg void OnBnClickedEight();
	afx_msg void OnBnClickedNine();
	afx_msg void OnBnClickedZero();
	afx_msg void OnBnClickedButton5();
	afx_msg void OnBnClickedButton9();
	afx_msg void OnBnClickedAdd();
	afx_msg void OnBnClickedReduce();
	afx_msg void OnBnClickedMuitiple();
	afx_msg void OnBnClickedDivision();
	afx_msg void OnBnClickedCalculate();
	afx_msg void OnBnClickedClear();
	double parameter;
	double result;
	bool isPoint;
	int sumPoint;
	char opera;
};
