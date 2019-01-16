
// FamilyDlg.h : 头文件
//

#pragma once
#include <string>
#include <vector>
#include "afxwin.h"
using namespace std;

struct income{
	int id;
	string name;
	string come;
	string year;
	string month;
	string day;
	double money;
};

inline bool compareIncome(const income& in1, const income& in2) {
	return in1.money < in2.money;
}

inline bool compareDate(const income& in1, const income& in2) {
	int year1 = atoi((in1.year).c_str());
	int year2 = atoi((in2.year).c_str());
	int month1 = atoi((in1.month).c_str());
	int month2 = atoi((in1.month).c_str());
	int day1 = atoi((in1.day).c_str());
	int day2 = atoi((in1.day).c_str());
	if(year1 < year2)
		return true;
	else if((year1 == year2) && (month1 < month2))
		return true;
	else if((year1 == year2) && (month1 == month2) && (day1 < day2))
		return true;
	else
		return false;
}

// CFamilyDlg 对话框
class CFamilyDlg : public CDialogEx
{
// 构造
public:
	CFamilyDlg(CWnd* pParent = NULL);	// 标准构造函数

// 对话框数据
	enum { IDD = IDD_FAMILY_DIALOG };

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
	afx_msg void OnBnClickedInAdd();
	afx_msg void OnBnClickedInModify();
	afx_msg void OnBnClickedInDelete();
    afx_msg void OnLbnSelchangeInList();
	afx_msg void OnBnClickedInSearch();
	afx_msg void OnBnClickedIncomeSort();
	afx_msg void OnBnClickedInDateSort();
	afx_msg void OnBnClickedInSerach();

	bool IsBeforeDate(CString cur_year,CString cur_month,CString cur_day,CString year,CString month,CString day);


	vector<income>income_vec;
	vector<income>income_search_vec;
	CListBox IN_LIST;
	CString in_start_year;
	CString in_start_month;
	CString in_start_day;
	CString in_end_year;
	CString in_end_month;
	CString in_end_day;
	double total_income;
	CString in_name_search;
	CString in_come_search;

	CListBox IN_SEARCH_LIST;
};
