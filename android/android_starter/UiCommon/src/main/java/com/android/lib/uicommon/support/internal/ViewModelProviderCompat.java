package com.android.lib.uicommon.support.internal;

import android.os.Looper;

import androidx.activity.ComponentActivity;
import androidx.fragment.app.Fragment;

import com.airbnb.mvrx.ActivityViewModelContext;
import com.airbnb.mvrx.FragmentViewModelContext;
import com.airbnb.mvrx.Mavericks;
import com.airbnb.mvrx.MavericksDelegateProvider;
import com.airbnb.mvrx.MavericksState;
import com.airbnb.mvrx.MavericksStateFactory;
import com.airbnb.mvrx.MavericksView;
import com.airbnb.mvrx.MavericksViewModel;
import com.airbnb.mvrx.MavericksViewModelProvider;
import com.airbnb.mvrx.RealMavericksStateFactory;
import com.airbnb.mvrx.ViewModelDoesNotExistException;
import com.airbnb.mvrx.lifecycleAwareLazy;

import org.jetbrains.annotations.NotNull;

import java.util.Locale;
import java.util.Objects;

import kotlin.Lazy;
import kotlin.jvm.functions.Function1;
import kotlin.reflect.KProperty;

import static com.airbnb.mvrx.MavericksExtensionsKt._fragmentArgsProvider;
import static com.android.lib.uicommon.DelegateKt._activityArgsProvider;
import static com.android.lib.uicommon.DelegateKt.getKClass;
import static com.android.lib.uicommon.DelegateKt.getKProperty;

/**
 * Created by liuqing.yang
 * 2021/5/15.
 * Email: 1239604859@qq.com
 */
public class ViewModelProviderCompat {
    public static <T extends Fragment & MavericksView,
            VM extends MavericksViewModel<S>,
            S extends MavericksState> VM fragmentViewModel(
            T fragment,
            Class<VM> viewModelClass,
            Class<S> stateClass
    ) {
        return viewModelDelegateProvider(
                viewModelClass,
                stateClass,
                false,
                stateFactory -> MavericksViewModelProvider.INSTANCE.get(
                        viewModelClass,
                        stateClass,
                        new FragmentViewModelContext(
                                fragment.requireActivity(),
                                _fragmentArgsProvider(fragment),
                                fragment,
                                fragment,
                                fragment.getSavedStateRegistry()
                        ),
                        viewModelClass.getName(),
                        false,
                        new RealMavericksStateFactory<>()
                )
        ).provideDelegate(fragment, getKProperty()).getValue();
    }

    public static <T extends Fragment & MavericksView,
            VM extends MavericksViewModel<S>,
            S extends MavericksState> VM parentFragmentViewModel(
            T fragment,
            Class<VM> viewModelClass,
            Class<S> stateClass
    ) {
        return viewModelDelegateProvider(
                viewModelClass,
                stateClass,
                true,
                stateFactory -> {
                    if (fragment.getParentFragment() == null) {
                        throw new ViewModelDoesNotExistException(String.format(
                                Locale.US,
                                "There is no parent fragment for %s so view model %s could not be found.",
                                fragment.getClass().getSimpleName(),
                                viewModelClass.getSimpleName()
                        ));
                    }
                    Fragment parent = fragment.getParentFragment();
                    String key = viewModelClass.getName();
                    while (parent != null) {
                        try {
                            return MavericksViewModelProvider.INSTANCE.get(
                                    viewModelClass,
                                    stateClass,
                                    new FragmentViewModelContext(
                                            fragment.requireActivity(),
                                            _fragmentArgsProvider(fragment),
                                            parent,
                                            parent,
                                            parent.getSavedStateRegistry()
                                    ),
                                    key,
                                    true,
                                    new RealMavericksStateFactory<>()

                            );
                        } catch (ViewModelDoesNotExistException e) {
                            parent = parent.getParentFragment();
                        }
                    }

                    Fragment topParentFragment = fragment.getParentFragment();
                    while (topParentFragment.getParentFragment() != null) {
                        topParentFragment = topParentFragment.getParentFragment();
                    }
                    FragmentViewModelContext viewModelContext = new FragmentViewModelContext(
                            fragment.requireActivity(),
                            _fragmentArgsProvider(fragment),
                            topParentFragment,
                            topParentFragment,
                            topParentFragment.getSavedStateRegistry()
                    );
                    return MavericksViewModelProvider.INSTANCE.get(
                            viewModelClass,
                            stateClass,
                            viewModelContext,
                            key,
                            false,
                            new RealMavericksStateFactory<>()
                    );
                }
        ).provideDelegate(fragment, getKProperty()).getValue();
    }

    public static <T extends Fragment & MavericksView,
            VM extends MavericksViewModel<S>,
            S extends MavericksState> VM targetFragmentViewModel(
            T fragment,
            Class<VM> viewModelClass,
            Class<S> stateClass
    ) {
        return viewModelDelegateProvider(
                viewModelClass,
                stateClass,
                true,
                stateFactory -> {
                    @SuppressWarnings("deprecation")
                    Fragment targetFragment = Objects.requireNonNull(
                            fragment.getTargetFragment(),
                            String.format(
                                    "There is no target fragment for %s!",
                                    fragment.getClass().getSimpleName()
                            )
                    );
                    return MavericksViewModelProvider.INSTANCE.get(
                            viewModelClass,
                            stateClass,
                            new FragmentViewModelContext(
                                    fragment.requireActivity(),
                                    _fragmentArgsProvider(fragment),
                                    targetFragment,
                                    targetFragment,
                                    targetFragment.getSavedStateRegistry()
                            ),
                            viewModelClass.getName(),
                            false,
                            new RealMavericksStateFactory<>()
                    );
                }
        ).provideDelegate(fragment, getKProperty()).getValue();
    }

    public static <T extends Fragment & MavericksView,
            VM extends MavericksViewModel<S>,
            S extends MavericksState> VM existingViewModel(
            T fragment,
            Class<VM> viewModelClass,
            Class<S> stateClass
    ) {
        return viewModelDelegateProvider(
                viewModelClass,
                stateClass,
                true,
                stateFactory -> MavericksViewModelProvider.INSTANCE.get(
                        viewModelClass,
                        stateClass,
                        new ActivityViewModelContext(
                                fragment.requireActivity(),
                                _fragmentArgsProvider(fragment),
                                fragment.requireActivity(),
                                fragment.requireActivity().getSavedStateRegistry()
                        ),
                        viewModelClass.getName(),
                        true,
                        new RealMavericksStateFactory<>()
                )
        ).provideDelegate(fragment, getKProperty()).getValue();
    }

    public static <T extends Fragment & MavericksView,
            VM extends MavericksViewModel<S>,
            S extends MavericksState> VM activityViewModel(
            T fragment,
            Class<VM> viewModelClass,
            Class<S> stateClass
    ) {
        return viewModelDelegateProvider(
                viewModelClass,
                stateClass,
                false,
                stateFactory -> MavericksViewModelProvider.INSTANCE.get(
                        viewModelClass,
                        stateClass,
                        new ActivityViewModelContext(
                                fragment.requireActivity(),
                                _fragmentArgsProvider(fragment),
                                fragment.requireActivity(),
                                fragment.requireActivity().getSavedStateRegistry()
                        ),
                        viewModelClass.getName(),
                        false,
                        new RealMavericksStateFactory<>()
                )
        ).provideDelegate(fragment, getKProperty()).getValue();
    }

    public static <T extends ComponentActivity & MavericksView,
            VM extends MavericksViewModel<S>,
            S extends MavericksState> VM viewModel(
            T activity,
            Class<VM> viewModelClass,
            Class<S> stateClass
    ) {
        return new lifecycleAwareLazy<>(
                activity,
                () -> Looper.myLooper() == Looper.getMainLooper(),
                () -> MavericksViewModelProvider.INSTANCE.get(
                        viewModelClass,
                        stateClass,
                        new ActivityViewModelContext(
                                activity,
                                _activityArgsProvider(activity),
                                activity,
                                activity.getSavedStateRegistry()
                        ),
                        viewModelClass.getName(),
                        false,
                        new RealMavericksStateFactory<>()
                )
        ).getValue();
    }

    private static <T extends Fragment & MavericksView,
            VM extends MavericksViewModel<S>,
            S extends MavericksState> MavericksDelegateProvider<T, VM> viewModelDelegateProvider(
            Class<VM> viewModelClass,
            Class<S> stateClass,
            boolean existingViewModel,
            Function1<MavericksStateFactory<VM, S>, VM> viewModelProvider
    ) {
        return new MavericksDelegateProvider<T, VM>() {
            @NotNull
            @Override
            public Lazy<VM> provideDelegate(T thisRef, @NotNull KProperty<?> kProperty) {
                return Mavericks.INSTANCE.getViewModelDelegateFactory().createLazyViewModel(
                        thisRef,
                        kProperty,
                        getKClass(viewModelClass),
                        viewModelClass::getName,
                        getKClass(stateClass),
                        existingViewModel,
                        viewModelProvider
                );
            }
        };
    }
}
