package service;

public interface CheckData
{
    // 檢查NOT NULL欄位是否為空
    public boolean checkNull();

    // 檢查各資料表規定形式
    public String checkFormat();
}
