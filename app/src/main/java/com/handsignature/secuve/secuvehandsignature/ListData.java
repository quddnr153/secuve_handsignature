package com.handsignature.secuve.secuvehandsignature;

import android.graphics.drawable.Drawable;
import android.widget.Button;

import java.text.Collator;
import java.util.Comparator;

/**
 * Created by quddn on 2016-08-13.
 */
public class ListData {
    /**
     * 리스트 정보를 담고 있을 객체 생성
     */
    // 아이콘
    public String userName;

    // 제목
    public Button modifyButton;

    // 날짜
    public Button deleteButton;

    /**
     * 알파벳 이름으로 정렬
     */
    public static final Comparator<ListData> ALPHA_COMPARATOR = new Comparator<ListData>() {
        private final Collator sCollator = Collator.getInstance();

        @Override
        public int compare(ListData mListDate_1, ListData mListDate_2) {
            return sCollator.compare(mListDate_1.userName, mListDate_2.userName);
        }
    };
}
