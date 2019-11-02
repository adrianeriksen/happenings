import React, { useEffect } from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import { connect } from 'react-redux';

import Events from './Events';
import Login from './Login';
import Header from '../components/header/Header';
import { fetchPrincipal } from '../actions/auth';

function App({ auth, fetchPrincipal }) {
  useEffect(() => {
    fetchPrincipal();
  }, [fetchPrincipal]);

  return (
    <Router>
      <div className="container">
        <Header
          isAuthenticated={auth.isAuthenticated}
          principal={auth.principal}
        />
        <Switch>
          <Route path="/" exact component={Events} />
          <Route path="/login" component={Login} />
        </Switch>
      </div>
    </Router>
  );
}

const mapStateToProps = state => ({
  auth: state.auth
});

const mapDispatchToProps = {
  fetchPrincipal
};

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(App);
