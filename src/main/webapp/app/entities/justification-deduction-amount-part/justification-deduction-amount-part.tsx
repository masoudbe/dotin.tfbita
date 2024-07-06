import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate, getSortState } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSort, faSortUp, faSortDown } from '@fortawesome/free-solid-svg-icons';
import { ASC, DESC, SORT } from 'app/shared/util/pagination.constants';
import { overrideSortStateWithQueryParams } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntities } from './justification-deduction-amount-part.reducer';

export const JustificationDeductionAmountPart = () => {
  const dispatch = useAppDispatch();

  const pageLocation = useLocation();
  const navigate = useNavigate();

  const [sortState, setSortState] = useState(overrideSortStateWithQueryParams(getSortState(pageLocation, 'id'), pageLocation.search));

  const justificationDeductionAmountPartList = useAppSelector(state => state.justificationDeductionAmountPart.entities);
  const loading = useAppSelector(state => state.justificationDeductionAmountPart.loading);

  const getAllEntities = () => {
    dispatch(
      getEntities({
        sort: `${sortState.sort},${sortState.order}`,
      }),
    );
  };

  const sortEntities = () => {
    getAllEntities();
    const endURL = `?sort=${sortState.sort},${sortState.order}`;
    if (pageLocation.search !== endURL) {
      navigate(`${pageLocation.pathname}${endURL}`);
    }
  };

  useEffect(() => {
    sortEntities();
  }, [sortState.order, sortState.sort]);

  const sort = p => () => {
    setSortState({
      ...sortState,
      order: sortState.order === ASC ? DESC : ASC,
      sort: p,
    });
  };

  const handleSyncList = () => {
    sortEntities();
  };

  const getSortIconByFieldName = (fieldName: string) => {
    const sortFieldName = sortState.sort;
    const order = sortState.order;
    if (sortFieldName !== fieldName) {
      return faSort;
    } else {
      return order === ASC ? faSortUp : faSortDown;
    }
  };

  return (
    <div>
      <h2 id="justification-deduction-amount-part-heading" data-cy="JustificationDeductionAmountPartHeading">
        <Translate contentKey="tfbitaApp.justificationDeductionAmountPart.home.title">Justification Deduction Amount Parts</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="tfbitaApp.justificationDeductionAmountPart.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link
            to="/justification-deduction-amount-part/new"
            className="btn btn-primary jh-create-entity"
            id="jh-create-entity"
            data-cy="entityCreateButton"
          >
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="tfbitaApp.justificationDeductionAmountPart.home.createLabel">
              Create new Justification Deduction Amount Part
            </Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {justificationDeductionAmountPartList && justificationDeductionAmountPartList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="tfbitaApp.justificationDeductionAmountPart.id">ID</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('id')} />
                </th>
                <th className="hand" onClick={sort('receiveTransactionCode')}>
                  <Translate contentKey="tfbitaApp.justificationDeductionAmountPart.receiveTransactionCode">
                    Receive Transaction Code
                  </Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('receiveTransactionCode')} />
                </th>
                <th className="hand" onClick={sort('returnTransactionCode')}>
                  <Translate contentKey="tfbitaApp.justificationDeductionAmountPart.returnTransactionCode">
                    Return Transaction Code
                  </Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('returnTransactionCode')} />
                </th>
                <th className="hand" onClick={sort('date')}>
                  <Translate contentKey="tfbitaApp.justificationDeductionAmountPart.date">Date</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('date')} />
                </th>
                <th className="hand" onClick={sort('amount')}>
                  <Translate contentKey="tfbitaApp.justificationDeductionAmountPart.amount">Amount</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('amount')} />
                </th>
                <th className="hand" onClick={sort('amountBasedOnRial')}>
                  <Translate contentKey="tfbitaApp.justificationDeductionAmountPart.amountBasedOnRial">Amount Based On Rial</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('amountBasedOnRial')} />
                </th>
                <th className="hand" onClick={sort('depositNumber')}>
                  <Translate contentKey="tfbitaApp.justificationDeductionAmountPart.depositNumber">Deposit Number</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('depositNumber')} />
                </th>
                <th className="hand" onClick={sort('receiveCurrencyCode')}>
                  <Translate contentKey="tfbitaApp.justificationDeductionAmountPart.receiveCurrencyCode">Receive Currency Code</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('receiveCurrencyCode')} />
                </th>
                <th className="hand" onClick={sort('currencyRateDate')}>
                  <Translate contentKey="tfbitaApp.justificationDeductionAmountPart.currencyRateDate">Currency Rate Date</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('currencyRateDate')} />
                </th>
                <th className="hand" onClick={sort('sellRate')}>
                  <Translate contentKey="tfbitaApp.justificationDeductionAmountPart.sellRate">Sell Rate</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('sellRate')} />
                </th>
                <th className="hand" onClick={sort('buyRate')}>
                  <Translate contentKey="tfbitaApp.justificationDeductionAmountPart.buyRate">Buy Rate</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('buyRate')} />
                </th>
                <th className="hand" onClick={sort('comment')}>
                  <Translate contentKey="tfbitaApp.justificationDeductionAmountPart.comment">Comment</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('comment')} />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.justificationDeductionAmountPart.justificationDeductionAmount">
                    Justification Deduction Amount
                  </Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {justificationDeductionAmountPartList.map((justificationDeductionAmountPart, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button
                      tag={Link}
                      to={`/justification-deduction-amount-part/${justificationDeductionAmountPart.id}`}
                      color="link"
                      size="sm"
                    >
                      {justificationDeductionAmountPart.id}
                    </Button>
                  </td>
                  <td>{justificationDeductionAmountPart.receiveTransactionCode}</td>
                  <td>{justificationDeductionAmountPart.returnTransactionCode}</td>
                  <td>{justificationDeductionAmountPart.date}</td>
                  <td>{justificationDeductionAmountPart.amount}</td>
                  <td>{justificationDeductionAmountPart.amountBasedOnRial}</td>
                  <td>{justificationDeductionAmountPart.depositNumber}</td>
                  <td>{justificationDeductionAmountPart.receiveCurrencyCode}</td>
                  <td>{justificationDeductionAmountPart.currencyRateDate}</td>
                  <td>{justificationDeductionAmountPart.sellRate}</td>
                  <td>{justificationDeductionAmountPart.buyRate}</td>
                  <td>{justificationDeductionAmountPart.comment}</td>
                  <td>
                    {justificationDeductionAmountPart.justificationDeductionAmount ? (
                      <Link to={`/justification-deduction-amount/${justificationDeductionAmountPart.justificationDeductionAmount.id}`}>
                        {justificationDeductionAmountPart.justificationDeductionAmount.id}
                      </Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button
                        tag={Link}
                        to={`/justification-deduction-amount-part/${justificationDeductionAmountPart.id}`}
                        color="info"
                        size="sm"
                        data-cy="entityDetailsButton"
                      >
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`/justification-deduction-amount-part/${justificationDeductionAmountPart.id}/edit`}
                        color="primary"
                        size="sm"
                        data-cy="entityEditButton"
                      >
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button
                        onClick={() =>
                          (window.location.href = `/justification-deduction-amount-part/${justificationDeductionAmountPart.id}/delete`)
                        }
                        color="danger"
                        size="sm"
                        data-cy="entityDeleteButton"
                      >
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && (
            <div className="alert alert-warning">
              <Translate contentKey="tfbitaApp.justificationDeductionAmountPart.home.notFound">
                No Justification Deduction Amount Parts found
              </Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default JustificationDeductionAmountPart;
