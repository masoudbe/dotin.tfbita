import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import PaymentCurrencyRateType from './payment-currency-rate-type';
import PaymentCurrencyRateTypeDetail from './payment-currency-rate-type-detail';
import PaymentCurrencyRateTypeUpdate from './payment-currency-rate-type-update';
import PaymentCurrencyRateTypeDeleteDialog from './payment-currency-rate-type-delete-dialog';

const PaymentCurrencyRateTypeRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<PaymentCurrencyRateType />} />
    <Route path="new" element={<PaymentCurrencyRateTypeUpdate />} />
    <Route path=":id">
      <Route index element={<PaymentCurrencyRateTypeDetail />} />
      <Route path="edit" element={<PaymentCurrencyRateTypeUpdate />} />
      <Route path="delete" element={<PaymentCurrencyRateTypeDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default PaymentCurrencyRateTypeRoutes;
