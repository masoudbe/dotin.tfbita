import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import PaymentItemType from './payment-item-type';
import PaymentItemTypeDetail from './payment-item-type-detail';
import PaymentItemTypeUpdate from './payment-item-type-update';
import PaymentItemTypeDeleteDialog from './payment-item-type-delete-dialog';

const PaymentItemTypeRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<PaymentItemType />} />
    <Route path="new" element={<PaymentItemTypeUpdate />} />
    <Route path=":id">
      <Route index element={<PaymentItemTypeDetail />} />
      <Route path="edit" element={<PaymentItemTypeUpdate />} />
      <Route path="delete" element={<PaymentItemTypeDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default PaymentItemTypeRoutes;
