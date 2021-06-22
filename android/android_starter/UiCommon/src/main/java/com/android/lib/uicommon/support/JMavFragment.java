package com.android.lib.uicommon.support;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;

import com.airbnb.mvrx.Async;
import com.airbnb.mvrx.BaseMvRxViewModel;
import com.airbnb.mvrx.DeliveryMode;
import com.airbnb.mvrx.MavericksState;
import com.airbnb.mvrx.MavericksView;
import com.airbnb.mvrx.MavericksViewModel;
import com.airbnb.mvrx.MvRxView;
import com.airbnb.mvrx.UniqueOnly;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import io.reactivex.disposables.Disposable;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.functions.Function6;
import kotlin.jvm.functions.Function7;
import kotlin.jvm.functions.Function8;
import kotlin.reflect.KProperty1;
import kotlinx.coroutines.Job;

/**
 * Created by cooper
 * 2021/5/14.
 * Email: 1239604859@qq.com
 */
public abstract class JMavFragment extends BaseFragment {
    public JMavFragment(@LayoutRes int contentLayoutId) {
        super(contentLayoutId);
    }

    @Override
    public final void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        onViewCreatedCompat(contentView, savedInstanceState);
    }

    protected abstract void onViewCreatedCompat(
            @NonNull View view,
            @Nullable Bundle savedInstanceState
    );

    @NotNull
    @Override
    public <S extends MavericksState, T> Disposable asyncSubscribe(
            @NotNull BaseMvRxViewModel<S> baseMvRxViewModel,
            @NotNull KProperty1<S, ? extends Async<? extends T>> kProperty1,
            @NotNull DeliveryMode deliveryMode,
            @Nullable Function1<? super Throwable, Unit> function1,
            @Nullable Function1<? super T, Unit> function11
    ) {
        return MvRxView.DefaultImpls.asyncSubscribe(
                this,
                baseMvRxViewModel,
                kProperty1,
                deliveryMode,
                function1,
                function11
        );
    }

    @NotNull
    @Override
    public <S extends MavericksState, A> Disposable selectSubscribe(
            @NotNull BaseMvRxViewModel<S> baseMvRxViewModel,
            @NotNull KProperty1<S, ? extends A> kProperty1,
            @NotNull DeliveryMode deliveryMode,
            @NotNull Function1<? super A, Unit> function1
    ) {
        return MvRxView.DefaultImpls
                .selectSubscribe(this, baseMvRxViewModel, kProperty1, deliveryMode, function1);
    }

    @NotNull
    @Override
    public <S extends MavericksState, A, B> Disposable selectSubscribe(
            @NotNull BaseMvRxViewModel<S> baseMvRxViewModel,
            @NotNull KProperty1<S, ? extends A> kProperty1,
            @NotNull KProperty1<S, ? extends B> kProperty11,
            @NotNull DeliveryMode deliveryMode,
            @NotNull Function2<? super A, ? super B, Unit> function2
    ) {
        return MvRxView.DefaultImpls.selectSubscribe(
                this,
                baseMvRxViewModel,
                kProperty1,
                kProperty11,
                deliveryMode,
                function2
        );
    }

    @NotNull
    @Override
    public <S extends MavericksState, A, B, C> Disposable selectSubscribe(
            @NotNull BaseMvRxViewModel<S> baseMvRxViewModel,
            @NotNull KProperty1<S, ? extends A> kProperty1,
            @NotNull KProperty1<S, ? extends B> kProperty11,
            @NotNull KProperty1<S, ? extends C> kProperty12,
            @NotNull DeliveryMode deliveryMode,
            @NotNull Function3<? super A, ? super B, ? super C, Unit> function3
    ) {
        return MvRxView.DefaultImpls.selectSubscribe(
                this,
                baseMvRxViewModel,
                kProperty1,
                kProperty11,
                kProperty12,
                deliveryMode,
                function3
        );
    }

    @NotNull
    @Override
    public <S extends MavericksState, A, B, C, D> Disposable selectSubscribe(
            @NotNull BaseMvRxViewModel<S> baseMvRxViewModel,
            @NotNull KProperty1<S, ? extends A> kProperty1,
            @NotNull KProperty1<S, ? extends B> kProperty11,
            @NotNull KProperty1<S, ? extends C> kProperty12,
            @NotNull KProperty1<S, ? extends D> kProperty13,
            @NotNull DeliveryMode deliveryMode,
            @NotNull Function4<? super A, ? super B, ? super C, ? super D, Unit> function4
    ) {
        return MvRxView.DefaultImpls.selectSubscribe(
                this,
                baseMvRxViewModel,
                kProperty1,
                kProperty11,
                kProperty12,
                kProperty13,
                deliveryMode,
                function4
        );
    }

    @NotNull
    @Override
    public <S extends MavericksState, A, B, C, D, E> Disposable selectSubscribe(
            @NotNull BaseMvRxViewModel<S> baseMvRxViewModel,
            @NotNull KProperty1<S, ? extends A> kProperty1,
            @NotNull KProperty1<S, ? extends B> kProperty11,
            @NotNull KProperty1<S, ? extends C> kProperty12,
            @NotNull KProperty1<S, ? extends D> kProperty13,
            @NotNull KProperty1<S, ? extends E> kProperty14,
            @NotNull DeliveryMode deliveryMode,
            @NotNull Function5<? super A, ? super B, ? super C, ? super D, ? super E, Unit> function5
    ) {
        return MvRxView.DefaultImpls.selectSubscribe(
                this,
                baseMvRxViewModel,
                kProperty1,
                kProperty11,
                kProperty12,
                kProperty13,
                kProperty14,
                deliveryMode,
                function5
        );
    }

    @NotNull
    @Override
    public <S extends MavericksState, A, B, C, D, E, F> Disposable selectSubscribe(
            @NotNull BaseMvRxViewModel<S> baseMvRxViewModel,
            @NotNull KProperty1<S, ? extends A> kProperty1,
            @NotNull KProperty1<S, ? extends B> kProperty11,
            @NotNull KProperty1<S, ? extends C> kProperty12,
            @NotNull KProperty1<S, ? extends D> kProperty13,
            @NotNull KProperty1<S, ? extends E> kProperty14,
            @NotNull KProperty1<S, ? extends F> kProperty15,
            @NotNull DeliveryMode deliveryMode,
            @NotNull Function6<? super A, ? super B, ? super C, ? super D, ? super E, ? super F, Unit> function6
    ) {
        return MvRxView.DefaultImpls.selectSubscribe(
                this,
                baseMvRxViewModel,
                kProperty1,
                kProperty11,
                kProperty12,
                kProperty13,
                kProperty14,
                kProperty15,
                deliveryMode,
                function6
        );
    }

    @NotNull
    @Override
    public <S extends MavericksState, A, B, C, D, E, F, G> Disposable selectSubscribe(
            @NotNull BaseMvRxViewModel<S> baseMvRxViewModel,
            @NotNull KProperty1<S, ? extends A> kProperty1,
            @NotNull KProperty1<S, ? extends B> kProperty11,
            @NotNull KProperty1<S, ? extends C> kProperty12,
            @NotNull KProperty1<S, ? extends D> kProperty13,
            @NotNull KProperty1<S, ? extends E> kProperty14,
            @NotNull KProperty1<S, ? extends F> kProperty15,
            @NotNull KProperty1<S, ? extends G> kProperty16,
            @NotNull DeliveryMode deliveryMode,
            @NotNull Function7<? super A, ? super B, ? super C, ? super D, ? super E, ? super F, ? super G, Unit> function7
    ) {
        return MvRxView.DefaultImpls.selectSubscribe(
                this,
                baseMvRxViewModel,
                kProperty1,
                kProperty11,
                kProperty12,
                kProperty13,
                kProperty14,
                kProperty15,
                kProperty16,
                deliveryMode,
                function7
        );
    }

    @NotNull
    @Override
    public <S extends MavericksState> Disposable subscribe(
            @NotNull BaseMvRxViewModel<S> baseMvRxViewModel,
            @NotNull DeliveryMode deliveryMode,
            @NotNull Function1<? super S, Unit> function1
    ) {
        return MvRxView.DefaultImpls.subscribe(this, baseMvRxViewModel, deliveryMode, function1);
    }

    @NotNull
    @Override
    public String getMvrxViewId() {
        return MavericksView.DefaultImpls.getMvrxViewId(this);
    }

    @NotNull
    @Override
    public LifecycleOwner getSubscriptionLifecycleOwner() {
        return MavericksView.DefaultImpls.getSubscriptionLifecycleOwner(this);
    }

    @Override
    public void postInvalidate() {
        MavericksView.DefaultImpls.postInvalidate(this);
    }

    @NotNull
    @Override
    public UniqueOnly uniqueOnly(@Nullable String s) {
        return MavericksView.DefaultImpls.uniqueOnly(this, s);
    }

    @NotNull
    @Override
    public <S extends MavericksState, T> Job onAsync(
            @NotNull MavericksViewModel<S> mavericksViewModel,
            @NotNull KProperty1<S, ? extends Async<? extends T>> kProperty1,
            @NotNull DeliveryMode deliveryMode,
            @Nullable Function2<? super Throwable, ? super Continuation<? super Unit>, ?> function2,
            @Nullable Function2<? super T, ? super Continuation<? super Unit>, ?> function21
    ) {
        return MavericksView.DefaultImpls.onAsync(
                this,
                mavericksViewModel,
                kProperty1,
                deliveryMode,
                function2,
                function21
        );
    }

    @NotNull
    @Override
    public <S extends MavericksState> Job onEach(
            @NotNull MavericksViewModel<S> mavericksViewModel,
            @NotNull DeliveryMode deliveryMode,
            @NotNull Function2<? super S, ? super Continuation<? super Unit>, ?> function2
    ) {
        return MavericksView.DefaultImpls.onEach(this, mavericksViewModel, deliveryMode, function2);
    }

    @NotNull
    @Override
    public <S extends MavericksState, A> Job onEach(
            @NotNull MavericksViewModel<S> mavericksViewModel,
            @NotNull KProperty1<S, ? extends A> kProperty1,
            @NotNull DeliveryMode deliveryMode,
            @NotNull Function2<? super A, ? super Continuation<? super Unit>, ?> function2
    ) {
        return MavericksView.DefaultImpls.onEach(
                this,
                mavericksViewModel,
                kProperty1,
                deliveryMode,
                function2
        );
    }

    @NotNull
    @Override
    public <S extends MavericksState, A, B> Job onEach(
            @NotNull MavericksViewModel<S> mavericksViewModel,
            @NotNull KProperty1<S, ? extends A> kProperty1,
            @NotNull KProperty1<S, ? extends B> kProperty11,
            @NotNull DeliveryMode deliveryMode,
            @NotNull Function3<? super A, ? super B, ? super Continuation<? super Unit>, ?> function3
    ) {
        return MavericksView.DefaultImpls.onEach(
                this,
                mavericksViewModel,
                kProperty1,
                kProperty11,
                deliveryMode,
                function3
        );
    }

    @NotNull
    @Override
    public <S extends MavericksState, A, B, C> Job onEach(
            @NotNull MavericksViewModel<S> mavericksViewModel,
            @NotNull KProperty1<S, ? extends A> kProperty1,
            @NotNull KProperty1<S, ? extends B> kProperty11,
            @NotNull KProperty1<S, ? extends C> kProperty12,
            @NotNull DeliveryMode deliveryMode,
            @NotNull Function4<? super A, ? super B, ? super C, ? super Continuation<? super Unit>, ?> function4
    ) {
        return MavericksView.DefaultImpls.onEach(
                this,
                mavericksViewModel,
                kProperty1,
                kProperty11,
                kProperty12,
                deliveryMode,
                function4
        );
    }

    @NotNull
    @Override
    public <S extends MavericksState, A, B, C, D> Job onEach(
            @NotNull MavericksViewModel<S> mavericksViewModel,
            @NotNull KProperty1<S, ? extends A> kProperty1,
            @NotNull KProperty1<S, ? extends B> kProperty11,
            @NotNull KProperty1<S, ? extends C> kProperty12,
            @NotNull KProperty1<S, ? extends D> kProperty13,
            @NotNull DeliveryMode deliveryMode,
            @NotNull Function5<? super A, ? super B, ? super C, ? super D, ? super Continuation<? super Unit>, ?> function5
    ) {
        return MavericksView.DefaultImpls.onEach(
                this,
                mavericksViewModel,
                kProperty1,
                kProperty11,
                kProperty12,
                kProperty13,
                deliveryMode,
                function5
        );
    }

    @NotNull
    @Override
    public <S extends MavericksState, A, B, C, D, E> Job onEach(
            @NotNull MavericksViewModel<S> mavericksViewModel,
            @NotNull KProperty1<S, ? extends A> kProperty1,
            @NotNull KProperty1<S, ? extends B> kProperty11,
            @NotNull KProperty1<S, ? extends C> kProperty12,
            @NotNull KProperty1<S, ? extends D> kProperty13,
            @NotNull KProperty1<S, ? extends E> kProperty14,
            @NotNull DeliveryMode deliveryMode,
            @NotNull Function6<? super A, ? super B, ? super C, ? super D, ? super E, ? super Continuation<? super Unit>, ?> function6
    ) {
        return MavericksView.DefaultImpls.onEach(
                this,
                mavericksViewModel,
                kProperty1,
                kProperty11,
                kProperty12,
                kProperty13,
                kProperty14,
                deliveryMode,
                function6
        );
    }

    @NotNull
    @Override
    public <S extends MavericksState, A, B, C, D, E, F> Job onEach(
            @NotNull MavericksViewModel<S> mavericksViewModel,
            @NotNull KProperty1<S, ? extends A> kProperty1,
            @NotNull KProperty1<S, ? extends B> kProperty11,
            @NotNull KProperty1<S, ? extends C> kProperty12,
            @NotNull KProperty1<S, ? extends D> kProperty13,
            @NotNull KProperty1<S, ? extends E> kProperty14,
            @NotNull KProperty1<S, ? extends F> kProperty15,
            @NotNull DeliveryMode deliveryMode,
            @NotNull Function7<? super A, ? super B, ? super C, ? super D, ? super E, ? super F, ? super Continuation<? super Unit>, ?> function7
    ) {
        return MavericksView.DefaultImpls.onEach(
                this,
                mavericksViewModel,
                kProperty1,
                kProperty11,
                kProperty12,
                kProperty13,
                kProperty14,
                kProperty15,
                deliveryMode,
                function7
        );
    }

    @NotNull
    @Override
    public <S extends MavericksState, A, B, C, D, E, F, G> Job onEach(
            @NotNull MavericksViewModel<S> mavericksViewModel,
            @NotNull KProperty1<S, ? extends A> kProperty1,
            @NotNull KProperty1<S, ? extends B> kProperty11,
            @NotNull KProperty1<S, ? extends C> kProperty12,
            @NotNull KProperty1<S, ? extends D> kProperty13,
            @NotNull KProperty1<S, ? extends E> kProperty14,
            @NotNull KProperty1<S, ? extends F> kProperty15,
            @NotNull KProperty1<S, ? extends G> kProperty16,
            @NotNull DeliveryMode deliveryMode,
            @NotNull Function8<? super A, ? super B, ? super C, ? super D, ? super E, ? super F, ? super G, ? super Continuation<? super Unit>, ?> function8
    ) {
        return MavericksView.DefaultImpls.onEach(
                this,
                mavericksViewModel,
                kProperty1,
                kProperty11,
                kProperty12,
                kProperty13,
                kProperty14,
                kProperty15,
                kProperty16,
                deliveryMode,
                function8
        );
    }
}
