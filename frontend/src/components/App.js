import React, { useEffect, useState } from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import { connect } from 'react-redux';

import { makeStyles } from '@material-ui/styles';
import { Container } from '@material-ui/core';

import Events from './Events';
import Login from './Login';
import UserContext from '../contexts/UserContext';
import Header from './Header';
import { fetchPrincipal } from '../actions/auth';

const useStyles = makeStyles({
  container: {
    marginTop: 64
  }
});

function App({ auth, fetchPrincipal }) {
  const classes = useStyles();

  const [currentUser, setCurrentUser] = useState(null);

  useEffect(() => {
    fetchPrincipal();
  }, [fetchPrincipal]);

  return (
    <UserContext.Provider value={{ currentUser, setCurrentUser }}>
      <Router>
        <>
          <Header
            isAuthenticated={auth.isAuthenticated}
            principal={auth.principal}
          />
          <Container maxWidth="sm" className={classes.container}>
            <Switch>
              <Route path="/" exact component={Events} />
              <Route path="/login" component={Login} />
            </Switch>
          </Container>
        </>
      </Router>
    </UserContext.Provider>
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
