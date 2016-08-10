package com.demo.data.repository.languages;

import com.demo.data.cache.db.AppDatabase;
import com.demo.data.cache.db.LanguageDbModel;
import com.demo.data.cache.db.LanguageDbModelMapper;
import com.demo.data.cache.db.LanguageModelToDbMapper;
import com.demo.domain.entity.Language;
import com.raizlabs.android.dbflow.config.DatabaseDefinition;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.Delete;
import com.raizlabs.android.dbflow.sql.language.Select;
import com.raizlabs.android.dbflow.structure.database.transaction.FastStoreModelTransaction;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by Beka on 8/3/16.
 */
public class LocalStorage implements LanguagesStore {
    private final LanguageDbModelMapper mLanguageDbModelMapper;
    private final LanguageModelToDbMapper mLanguageModelToDbMapper;

    @Inject
    public LocalStorage(LanguageDbModelMapper languageDbModelMapper, LanguageModelToDbMapper languageModelToDbMapper) {
        mLanguageDbModelMapper = languageDbModelMapper;
        mLanguageModelToDbMapper = languageModelToDbMapper;
    }

    @Override
    public Observable<List<Language>> loadLanguageList() {
        List<LanguageDbModel> languageDbModelList = new Select().from(LanguageDbModel.class).queryList();
        return  Observable.just(languageDbModelList).map(mLanguageDbModelMapper::map);
    }

    @Override
    public void saveLanguages(List<Language> languages) {
        FastStoreModelTransaction<LanguageDbModel> transaction = FastStoreModelTransaction
                .insertBuilder(FlowManager.getModelAdapter(LanguageDbModel.class))
                .addAll(mLanguageModelToDbMapper.map(languages))
                .build();

        DatabaseDefinition database = FlowManager.getDatabase(AppDatabase.class);
        database.executeTransaction(transaction);
    }

    @Override
    public void clean() {
        Delete.table(LanguageDbModel.class);
    }
}
