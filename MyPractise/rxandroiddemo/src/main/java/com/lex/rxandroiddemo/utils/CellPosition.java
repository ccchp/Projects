package com.lex.rxandroiddemo.utils;

/**
 * Created by Lex lex on 2017/4/4.
 */
public class CellPosition {
    private String _position = "";
    private String _startLetter = "";
    private String _endLtter = "";
    private int _startNumber = 0;
    private int _endNumber = 0;
    private String _currentLetter;
    private int _currentNumber;

    /**
     * ֻ只支持两位以内字母（例：A1、AB1）
     *
     * @param position 单元格位置（例：A1:A10）
     */
    public CellPosition(String position) {
        _position = position;
        SplitCellPosition();
    }

//C# TO JAVA CONVERTER TODO TASK: There is no preprocessor in Java:
    ///#region 公有方法及属性

    /**
     * 长度
     */
    public final int getLength() {
        if (_startLetter.equals(_endLtter)) {
            return _endNumber - _startNumber + 1;
        } else {
            return GetColumn(_endLtter) - GetColumn(_startLetter) + 1;
        }
    }

    /**
     *当前位置值
     */
    public final String getCurrent() {
        return _currentLetter + (new Integer(_currentNumber)).toString();
    }

    /**
     *向下移一位
     */
    public final void Next() {
        if (_startLetter.equals(_endLtter)) //����
        {
            _currentNumber = _currentNumber + 1;
        } else {
            _currentLetter = GetNextColumn(_currentLetter);
        }
    }

//C# TO JAVA CONVERTER TODO TASK: There is no preprocessor in Java:
    ///#endregion

//C# TO JAVA CONVERTER TODO TASK: There is no preprocessor in Java:
    ///#region 私有方法

    /**
     * 分割单元格位置
     */
    private void SplitCellPosition() {
        String[] splitCellPosition = _position.split("[:]", -1).toArray();
        int letterNum = 2;
        if (IsNumber(splitCellPosition[0].substring(1, 2))) {
            letterNum = 1;
        }
        _startLetter = splitCellPosition[0].substring(0, letterNum);
        _endLtter = splitCellPosition[1].substring(0, letterNum);
        _startNumber = Short.parseShort(splitCellPosition[0].substring(letterNum));
        _endNumber = Short.parseShort(splitCellPosition[1].substring(letterNum));
        _currentLetter = _startLetter;
        _currentNumber = _startNumber;
    }

    /**
     * 获取下一列（的字母）
     *
     * @param column
     * @return
     */
    private String GetNextColumn(String column) {
        ASCIIEncoding asciiEncoding = new ASCIIEncoding();
        String strCharacter = "";
        if (asciiEncoding.GetBytes(column).getLength() == 1) {
            int intAsciiCode = (int) asciiEncoding.GetBytes(column)[0];
            intAsciiCode = intAsciiCode + 1;
            byte[] byteArray = new byte[]{(byte) intAsciiCode};
            strCharacter = asciiEncoding.GetString(byteArray);
        } else if (asciiEncoding.GetBytes(column).getLength() == 2) {
            int intAsciiCode = (int) asciiEncoding.GetBytes(column)[1];
            intAsciiCode = intAsciiCode + 1;
            byte[] byteArray = new byte[]{(byte) intAsciiCode};
            strCharacter = asciiEncoding.GetString(byteArray);
            if (strCharacter.equals("[")) {
                int preAsciiCode = (int) asciiEncoding.GetBytes(column)[0];
                preAsciiCode = preAsciiCode + 1;
                byteArray = new byte[]{(byte) preAsciiCode};
                strCharacter = asciiEncoding.GetString(byteArray);
                strCharacter = strCharacter + "A";
            } else {
                strCharacter = column.substring(0, 1) + strCharacter;
            }
        }
        return strCharacter;
    }

    /**
     * 是否为数字
     *
     * @param message
     * @return
     */
    private boolean IsNumber(String message) {
        System.Text.RegularExpressions.Regex rex = new System.Text.RegularExpressions.Regex("^\\d+$");
        int result = -1;
        if (rex.IsMatch(message)) {
            result = Integer.parseInt(message);
            return true;
        } else {
            return false;
        }
    }

    /**
     * 返回列号
     *
     * @param character
     * @return
     */
    private int GetColumn(String character) {
        ASCIIEncoding asciiEncoding = new ASCIIEncoding();
        int startAsciiCode = (int) asciiEncoding.GetBytes("A")[0];
        int result = -1;
        if (character.length() == 1) {
            int intAsciiCode = (int) asciiEncoding.GetBytes(character)[0];
            result = intAsciiCode - startAsciiCode + 1;
        } else if (character.length() == 2) {
            int firstAsciiCode = (int) asciiEncoding.GetBytes(character.substring(0, 1))[0];
            int first = firstAsciiCode - startAsciiCode + 1;
            int secondAsciiCode = (int) asciiEncoding.GetBytes(character.substring(1))[1];
            int second = secondAsciiCode - startAsciiCode + 1;
            result = first * 26 + second;
        }
        return result;
    }

//C# TO JAVA CONVERTER TODO TASK: There is no preprocessor in Java:
    ///#endregion
}
