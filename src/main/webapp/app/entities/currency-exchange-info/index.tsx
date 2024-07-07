import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import CurrencyExchangeInfo from './currency-exchange-info';
import CurrencyExchangeInfoDetail from './currency-exchange-info-detail';
import CurrencyExchangeInfoUpdate from './currency-exchange-info-update';
import CurrencyExchangeInfoDeleteDialog from './currency-exchange-info-delete-dialog';

const CurrencyExchangeInfoRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<CurrencyExchangeInfo />} />
    <Route path="new" element={<CurrencyExchangeInfoUpdate />} />
    <Route path=":id">
      <Route index element={<CurrencyExchangeInfoDetail />} />
      <Route path="edit" element={<CurrencyExchangeInfoUpdate />} />
      <Route path="delete" element={<CurrencyExchangeInfoDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default CurrencyExchangeInfoRoutes;
