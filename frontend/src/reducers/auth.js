import { createReducer } from 'redux-starter-kit';
import {
  fetchPrincipalInitiatedAction,
  fetchPrincipalSuccessAction,
  authenticationInitiatedAction,
  authenticationSuccessAction,
  authenticationErrorAction,
  clearPrincipalAction
} from '../actions/auth';

const initialState = {
  isAuthenticated: false,
  isError: false,
  isLoading: false,
  principal: null
};

export default createReducer(initialState, {
  [authenticationInitiatedAction]: state => ({
    ...state,
    principal: null,
    isAuthenticated: false,
    isError: false,
    isLoading: true
  }),
  [authenticationSuccessAction]: (state, action) => ({
    ...state,
    principal: action.payload,
    isAuthenticated: true,
    isLoading: false
  }),
  [authenticationErrorAction]: state => ({
    ...state,
    isError: true,
    isLoading: false
  }),
  [fetchPrincipalInitiatedAction]: state => ({
    ...state,
    principal: null
  }),
  [fetchPrincipalSuccessAction]: (state, action) => ({
    ...state,
    principal: action.payload,
    isAuthenticated: true
  }),
  [clearPrincipalAction]: () => ({
    ...initialState
  })
});
